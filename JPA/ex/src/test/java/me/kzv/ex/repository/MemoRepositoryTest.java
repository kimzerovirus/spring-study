package me.kzv.ex.repository;

import me.kzv.ex.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kimzerovirus on 2021-11-18
 */

@SpringBootTest
public class MemoRepositoryTest {

	@Autowired
	MemoRepository memoRepository;

	@Test
	public void testClass() {
		System.out.println(memoRepository.getClass().getName());
	}

	@Transactional
	@Test
	public void testInsertDummies() {
	//JAVA8에서 반복문을 구현하는 색다른방법: IntStream과 LongStream에는 range와 rangeClosed 메소드가 있는데 Closed가 붙으면 끝 개수를 포함한다(<=과 <의 차이).

		IntStream.rangeClosed(1, 100).forEach(i -> {
			Memo memo = Memo.builder()
					.memoText("Sample..." + i)
					.build();

			memoRepository.save(memo);
		});
	}

	@Test
	public void testSelect(){
		//DB에 존재하는 mno
		Long mno = 100L;

		Optional<Memo> result = memoRepository.findById(mno);

		System.out.println("========================");
		if(result.isPresent()){
			Memo memo = result.get();
			System.out.println(memo);
		}
	}

	@Transactional
	@Test
	public void testSelect2(){
		//DB에 존재하는 mno
		Long mno = 100L;

		Memo memo = memoRepository.getOne(mno);

		System.out.println("========================");
		System.out.println(memo);
	}

	@Test
	public void testUpdate(){
		Memo memo = Memo.builder()
				.mno(100L)
				.memoText("Update Text")
				.build();
	}

	@Test
	public void testDelete(){
		Long mno = 100L;

		memoRepository.deleteById(mno);
	}

	@Test
	public void testPageDefault(){

		Pageable pageable = PageRequest.of(0, 10);

		Page<Memo> result = memoRepository.findAll(pageable);
		System.out.println(result);
		System.out.println("============================================");
		result.getTotalElements();
		result.getTotalPages();
		result.getNumber();
		result.getSize();
		result.getSort();
		result.getContent();
		result.hasNext();
		result.isFirst();
		result.isLast();
	}

	@Test
	public void testSort(){
		Sort sort1 = Sort.by("mno").descending();

		Pageable pageable = PageRequest.of(0, 10, sort1);

		Page<Memo> result = memoRepository.findAll(pageable);

		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}

	@Test
	public void testQueryMethods(){
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

		for(Memo memo : list){
			System.out.println(memo);
		}
	}

	@Test
	public void testQueryMethodWithPageable(){
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

		Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

		result.get().forEach(memo-> System.out.println(memo));
	}

	@Commit
	@Transactional
	public void testDeleteQueryMethods(){
		memoRepository.deleteMemoByMnoLessThan(10L);
	}
}