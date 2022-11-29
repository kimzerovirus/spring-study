package me.kzv.ecommerce.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.constant.ItemSellStatus;
import me.kzv.ecommerce.dto.ItemSearchDto;
import me.kzv.ecommerce.dto.MainItemDto;
import me.kzv.ecommerce.dto.QMainItemDto;
import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.QItem;
import me.kzv.ecommerce.entity.QItemImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;


/**
 * https://velog.io/@shining_dr/Querydsl-Repository-expansion
 * querydslrepositorysupport vs jpaqueryfactory
 * <p>
 * Q class 임포트 안될 때 https://velog.io/@re-deok/Intellij%EC%97%90%EC%84%9C-Querydsl-%EC%82%AC%EC%9A%A9%EC%8B%9C-Q-Class-import-%EB%AC%B8%EC%A0%9C
 * project structure -> import 할 폴더 -> 우클릭 source
 */
@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {

//    private JPAQueryFactory queryFactory;
//
//    public CustomItemRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }

    private final JPAQueryFactory queryFactory; // bean 으로 엔티티메니저를 등록하였다

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto dto, Pageable pageable) {
        QItem item = QItem.item;

        List<Item> content = queryFactory
                .selectFrom(item)
                .where(
                        regDtsAfter(dto.getSearchDateType()),
                        searchSellStatusEq(dto.getSearchSellStatus()),
                        searchByLike(dto.getSearchBy(), dto.getSearchQuery())
                )
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); //fetchResults() -- deprecated -- return type = QueryResults<Item>

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .where(
                        regDtsAfter(dto.getSearchDateType()),
                        searchSellStatusEq(dto.getSearchSellStatus()),
                        searchByLike(dto.getSearchBy(), dto.getSearchQuery())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne); // countQuery::fetchOne = () -> countQuery.fetchOne()
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    // 엔티티가 아닌 dto 로 반환 받기 위해서는 dto 에 @QueryProjection 어노테이션이 필요하다
    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto dto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        List<MainItemDto> content = queryFactory
                .select(new QMainItemDto(
                        item.id,
                        item.itemNm,
                        item.itemDetail,
                        itemImg.imgUrl,
                        item.price)
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(dto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(dto.getSearchQuery()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression itemNmLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }
}
