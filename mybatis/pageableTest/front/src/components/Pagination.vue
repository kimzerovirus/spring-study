<template>
  <div class="paginate">
    <a
      v-show="pageable.totalPages != 0"
      class="pre_end cursor"
      rel="tooltip"
      @click="goPage(0)"
    >
      처음
    </a>
    <a
      v-show="pageable.first == false"
      class="pre cursor"
      rel="tooltip"
      @click="goPage((pageable.pageNumber -= 1))"
    >
      이전
    </a>

    <a
      @click="goPage(pageNum - 1)"
      v-for="pageNum in pageCount()"
      :key="pageNum"
      :class="pageNum - 1 == pageable.pageNumber ? 'active' : 'cursor'"
    >
      {{ pageNum }}
    </a>

    <a
      v-show="pageable.last == false"
      class="next cursor"
      rel="tooltip"
      @click="goPage((pageable.pageNumber += 1))"
    >
      다음
    </a>
    <a
      v-show="pageable.totalPages != 0"
      class="next_end cursor"
      rel="tooltip"
      @click="goPage(pageable.totalPages - 1)"
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
      // var totalPage = this.pageable.totalPages;    // 총페이지 && endPage

      let list = [];

      console.log('PAGE ::', this.pageable);

      let startNum = 0,
        endNum = 0;
      if (this.pageable.totalPages > 5 && this.pageable.pageNumber == 3) {
        startNum = this.pageable.pageNumber - 1;
        endNum = this.pageable.pageNumber + 3;
      } else if (this.pageable.totalPages <= 5) {
        startNum = 1;
        endNum = this.pageable.totalPages;
      } else if (this.pageable.pageNumber <= 3) {
        startNum = 1;
        endNum = 5;
      } else if (this.pageable.pageNumber >= this.pageable.totalPages - 3) {
        startNum = this.pageable.totalPages - 4;
        endNum = this.pageable.totalPages;
      } else {
        startNum = this.pageable.pageNumber - 1;
        endNum = this.pageable.pageNumber + 3;
      }

      for (let i = startNum; i <= endNum; i++) {
        list.push(i);
      }

      return list;
    },
    goPage(pageNum) {
      console.log(pageNum, this.pageable.totalPages);
      this.$emit('goPage', pageNum);
    },
  },
};
</script>
<style scoped>
/* paginate */
.paginate {
	margin: 20px 0 0;
	text-align: center;
	clear: both;
	line-height: 16px;
}
.paginate a.pre_end,
.paginate a.next_end,
.paginate a.pre,
.paginate a.next {
	width: 40px;
	padding: 11px;
	text-indent: -999px;
	overflow: hidden;
	background-color: #fff;
}
.paginate a.pre {
	background: #fff url('../assets/images/ico_paging_prev.png') no-repeat 50% 50%;
}
.paginate a.next {
	background: #fff url('../assets/images/ico_paging_next.png') no-repeat 50% 50%;
}
.paginate a.pre_end {
	background: #fff url('../assets/images/ico_paging_first.png') no-repeat 50%
		50%;
}
.paginate a.next_end {
	background: #fff url('../assets/images/ico_paging_last.png') no-repeat 50% 50%;
}
.paginate a.pre_end:hover,
.paginate a.next_end:hover,
.paginate a.pre:hover,
.paginate a.next:hover {
	background-color: #fff;
	border: 1px solid #e5e5e5;
}
.paginate a {
	display: inline-block;
	padding: 11px 15px;
	margin: 0 1px;
	font-size: 13px;
	color: #555;
	text-align: center;
	border: 1px solid #e5e5e5;
	vertical-align: middle;
	background: #fff;
	font-family: Arial;
}
.paginate a.active,
.paginate a:hover {
	color: #fff;
	background-color: #555;
	border: 1px solid #555;
}
</style>
