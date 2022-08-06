<template>
	<div class="container">
		<div class="select-box">
			<select v-model="selected" :key="i" class="form-select">
				<option v-for="(filter, i) in filters" :key="i" :value="filter.value">
					{{ filter.name }}
				</option>
			</select>
		</div>
		<input v-model="keyword" class="form-control" type="text" placeholder="" @keyup.enter="search" />
		<button class="btn btn-primary" @click="search">
			<i class="fa-solid fa-magnifying-glass"></i>
		</button>
	</div>
</template>

<script>
const filters = [
	{ name: '전체 검색', value: 'all' },
	{ name: '질문게시판', value: 'qna' },
	{ name: '자유게시판', value: 'free' },
];

export default {
	data() {
		return {
			keyword: '',
			filters,
			selected: filters[0].value,
		};
	},
	methods: {
		search() {},
	},
};
</script>

<style lang="scss" scoped>
.container {
	display: flex;
	> * {
		margin-right: 10px;
		font-size: 15px;
		&:last-child {
			margin-right: 0;
		}
	}
	.select-box {
		display: flex;
		select {
			width: 120px;
			margin-right: 10px;
			&:last-child {
				margin-right: 0;
			}
		}
	}
	.btn {
		width: 120px;
		height: 50px;
		flex-shrink: 0;
		font-weight: 700;
	}
}
</style>
