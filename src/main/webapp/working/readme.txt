package.json，除了构建工具的版本可以不固定，工程的源码库版本
一定不能轻易更改，不然有可能会出现兼容性问题，
就比如$http.get().success()在新版angularJS中被废弃了，则之前写的所有代码都必须做
必要的更改。