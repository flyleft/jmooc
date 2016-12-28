var webpack = require('webpack')
var path = require('path');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var SRC_PATH = path.resolve(__dirname, 'src');
var DIST_PATH = path.resolve(__dirname, 'dist');

module.exports = {
    //入口文件配置
    entry: {
        index: './src/index.js',
    },
    //出口文件配置
    output: {
        path: './dist/js',
        filename: '[name].js',
    },
    module: {
        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            },
            {
                test: /\.(png|jpe?g|gif|svg)(\?\S*)?$/,
                loader: 'file-loader',
                query: {
                    name: '[name].[ext]?[hash]'
                }
            }
        ]
    },
    babel: {
        presets: ['es2015'],
        plugins: []
    },
    vue: {
        loaders: {
            js: 'babel'
        }
    },
    plugins: [
        new CopyWebpackPlugin([
            /*{ from: path.resolve(SRC_PATH, 'assets'), to: path.resolve(DIST_PATH, 'assets') },*/
           /* { from: path.resolve(SRC_PATH, 'index.html'), to: path.resolve(DIST_PATH, 'index.html') }*/
        ])
    ]
}