var webpack = require('webpack')
var path = require('path');

module.exports = {
    //入口文件配置
    entry: {
        index: './src/Component/entry.js',
    },
    //出口文件配置
    output: {
        path: './dist/js/',
        filename: '[name].js',
    },
    module: {
        loaders: [
            {test: /\.vue$/, loader: 'vue'},
            {test: /\.css$/, loader: 'style!css'},
            {test: /\.js$/, loader: 'babel', exclude: /node_modules/}
        ]
    }
}