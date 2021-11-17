package me.kzv.ex.repository;

import me.kzv.ex.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}