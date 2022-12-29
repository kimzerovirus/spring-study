package me.kzv.ecommerce.service;

import me.kzv.ecommerce.constant.ItemSellStatus;
import me.kzv.ecommerce.dto.ItemFormDto;
import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.ItemImg;
import me.kzv.ecommerce.repository.ItemImgRepository;
import me.kzv.ecommerce.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        IntStream.rangeClosed(1,5).forEach(i ->{
            String path = "/users/zerovirus/desktop/code/springstudy/jpa/sample-project/shop/upload/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        });

        return multipartFileList;
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void 상품등록_테스트() throws Exception {
        //given
        ItemFormDto dto = new ItemFormDto();
        dto.setItemNm("test");
        dto.setItemSellStatus(ItemSellStatus.SELL);
        dto.setItemDetail("test");
        dto.setPrice(10000);
        dto.setStockNumber(100);

        List<MultipartFile> multipartFiles = createMultipartFiles();
        Long itemId = itemService.saveItem(dto, multipartFiles);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        //when


        //then
        assertEquals(dto.getItemNm(), item.getItemNm());
        assertEquals(dto.getItemSellStatus(), item.getItemSellStatus());
        assertEquals(dto.getItemDetail(), item.getItemDetail());
        assertEquals(dto.getPrice(), item.getPrice());
        assertEquals(dto.getStockNumber(), item.getStockNumber());
        assertEquals(multipartFiles.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
    }
}