<template>
	<div class="paginate">
		<a
			v-show="pageable.pageCount != 0"
			class="pre_end"
			rel="tooltip"
			@click="goPage(0)"
		>
			처음
		</a>
		<a
			v-show="pageable.first == false"
			class="pre"
			rel="tooltip"
			@click="goPage((pageable.pageNumber -= 1))"
		>
			이전
		</a>

		<a
			@click="goPage(pageNum - 1)"
			v-for="pageNum in pageCount()"
			:key="pageNum"
			:class="pageNum - 1 == pageable.pageNumber ? 'active' : ''"
		>
			{{ pageNum }}
		</a>

		<a
			v-show="pageable.last == false"
			class="next"
			rel="tooltip"
			@click="goPage((pageable.pageNumber += 1))"
		>
			다음
		</a>
		<a
			v-show="pageable.pageCount != 0"
			class="next_end"
			rel="tooltip"
			@click="goPage(pageable.pageCount - 1)"
		>
			마지막
		</a>
	</div>
</template>

<script>
export default {
	props: ['pageable'],
	data() {
		return {
			currentPage: '',
		};
	},
	methods: {
		pageCount() {
			// var totalElements = this.pageable.totalElements;    // 총게시글 갯수
			// var listSize = this.pageable.pageSize;      // 몇개씩 보여질지 (게시글 갯수)
			// var currentPage = this.pageable.pageNumber    // 현재페이지 --> pageCount -1
			// var pageCount = 10;     // 보여질 페이지 번호 갯수
			// var totalPage = this.pageable.pageCount;    // 총페이지 && endPage

			let list = [];

			console.log('PAGE ::', this.pageable);

			let startNum = 0,
				endNum = 0;
			if (this.pageable.pageCount <= 5) {
				startNum = 1;
				endNum = this.pageable.pageCount;
			} else if (this.pageable.pageNumber <= 3) {
				startNum = 1;
				endNum = 5;
			} else if (this.pageable.pageNumber >= this.pageable.pageCount - 3) {
				startNum = this.pageable.pageCount - 4;
				endNum = this.pageable.pageCount;
			} else {
				startNum = this.pageable.pageNumber - 2;
				endNum = this.pageable.pageNumber + 2;
			}

			for (let i = startNum; i <= endNum; i++) {
				list.push(i);
			}

			return list;
		},
		goPage(pageNum) {
			this.$emit('goPage', pageNum);
		},
	},
};
</script>

<style></style>
