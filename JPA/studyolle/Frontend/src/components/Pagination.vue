<template>
	<div class="paginate">
		<a v-show="pageable.totalPages != 0" class="pre_end cursor" @click="goPage(0)">
			<i class="fa-solid fa-angles-left"></i>
		</a>
		<a v-show="pageable.first == false" class="pre cursor" @click="goPage((pageable.pageNumber -= 1))">
			<i class="fa-solid fa-angle-left"></i>
		</a>

		<a
			@click="goPage(pageNum - 1)"
			v-for="pageNum in pageCount()"
			:key="pageNum"
			:class="pageNum - 1 == pageable.pageNumber ? 'active' : 'cursor'"
		>
			{{ pageNum }}
		</a>

		<a v-show="pageable.last == false" class="next cursor" @click="goPage((pageable.pageNumber += 1))">
			<i class="fa-solid fa-angle-right"></i>
		</a>
		<a v-show="pageable.totalPages != 0" class="next_end cursor" @click="goPage(pageable.totalPages - 1)">
			<i class="fa-solid fa-angles-right"></i>
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
			let list = [];
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
<style lang="scss" scoped>
.paginate {
	margin: 20px 0 0;
	text-align: center;
	clear: both;
	line-height: 16px;

	a {
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
		text-decoration: none;
		cursor: pointer;

		&.pre_end:hover,
		&.next_end:hover,
		&.pre:hover,
		&.next:hover {
			background-color: #fff;
			border: 1px solid #e5e5e5;
		}
		&.active,
		&:hover {
			color: #fff;
			background-color: #555 !important;
			border: 1px solid #555;
		}
	}
}
</style>
