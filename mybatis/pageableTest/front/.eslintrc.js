module.exports = {
	root: true,
	env: {
		node: true,
	},
	extends: ['plugin:vue/essential', 'eslint:recommended', '@vue/prettier'],
	parserOptions: {
		parser: 'babel-eslint',
	},
	rules: {
		'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off', //console이 있으면 error
		'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off', // prettier설정은 eslint와 충돌날 수가 있으므로 .eslintrc.js로 만들기보다는 안에 포함시키는게 좋다
		'prettier/prettier': [
			'error',
			{
				singleQuote: true, //작은 따옴표
				semi: true, //세미콜론
				useTabs: true,
				tabWidth: 2,
				trailingComma: 'all',
				printWidth: 80, // 80자 마다 개행
				bracketSpacing: true,
				arrowParens: 'avoid',
				endOfLine: 'auto',
			},
		],
	},
	overrides: [
		{
			files: [
				'**/__tests__/*.{j,t}s?(x)',
				'**/tests/unit/**/*.spec.{j,t}s?(x)',
			],
			env: {
				jest: true,
			},
		},
	],
};