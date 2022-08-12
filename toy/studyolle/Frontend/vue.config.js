const { defineConfig } = require('@vue/cli-service');
const TerserPlugin = require('terser-webpack-plugin');

const path = require('path');
module.exports = defineConfig({
	transpileDependencies: true,
	outputDir: path.resolve(__dirname, '../backend/src/main/resources/static'),
	// indexPath: path.resolve(__dirname, '../src/main/resources/static/index.html'),
	devServer: {
		client: {
			overlay: false,
		},
	},
	configureWebpack: config => {
		if (process.env.NODE_ENV === 'production') {
			config.optimization = {
				minimize: true,
				minimizer: [
					new TerserPlugin({
						terserOptions: {
							ecma: 6,
							compress: { drop_console: true },
							output: { comments: false },
						},
					}),
				],
			};
		}
	},
});
