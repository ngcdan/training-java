const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  mode: process.env.NODE_ENV || 'development',
  entry: ["@babel/polyfill", path.join(__dirname, 'src', 'index.js')],
  devtool: 'inline-source-map',
  output: {
    path: path.resolve(__dirname, 'build'),
    publicPath: "/",
    filename: 'bundle.js'
  },
  resolve: {
    modules: [path.resolve(__dirname, 'src'), path.resolve(__dirname, 'public'),
      'node_modules']
  },
  devServer: {
    stats: "minimal",
    compress: true, //Enable gzip
    overlay: true,
    historyApiFallback: true,
    disableHostCheck: true,
    headers: {"Access-Control-Allow-Origin": "*"},
    https: false,
    contentBase: path.join(__dirname, 'public')
  },
  plugins: [
    new HtmlWebpackPlugin(
      {
        template: path.join(__dirname, 'public', 'index.html'),
      }
    )
  ],
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader']
      },
      {
        test: /\.(css|scss)$/i,
        use: [
          // style-loader comes first and followed by css-loader
          'style-loader', 'css-loader',
          // compiles Sass to CSS, using Node Sass by default
          "sass-loader"
        ],
      },
    ],
  }
}