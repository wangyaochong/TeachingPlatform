var gulpModules = {
    gulp: require('gulp'),
    gulpUtil: require('gulp-util'),
    gulpJade: require('gulp-jade'),
    gulpConcat: require('gulp-concat'),
    gulpJshint: require('gulp-jshint'),
    gulpReplace: require('gulp-replace'),
    gulpHtml2jade: require('gulp-html2jade'),
    gulpPlumber: require('gulp-plumber')
}
var processPath = {
    js2oneSrcPath: ['../raw/js/begin.js', '../view/1state_Js/*.js', '../raw/js/end.js', '../view/2angular_component_Js/**/*.js'],
    js2onePath: '../view/js/',
    js2oneLibPath: [
        //jquery
        '../raw/jslib/jquery.js',
        //bootstrap
        '../raw/jslib/bootstrap.js',
        //angularJS
        '../raw/jslib/angular.js',
        //ngtable
        '../raw/jslib/ng-table.js',
        //angular-ui-router
        '../raw/jslib/angular-ui-router.js',
        //datepicker#日期选择插件
        '../raw/jslib/bootstrap-datepicker.js',
        '../raw/jslib/bootstrap-datepicker.zh-CN.min.js',
        //autosize#textarea自动变化尺寸插件
        '../raw/jslib/autosize.js',
        //slick#滑动图片插件
        '../raw/jslib/slick.js',
        //bootstrap-fileinput#文件上传插件
        '../raw/jslib/canvas-to-blob.js',
        '../raw/jslib/sortable.js',
        '../raw/jslib/fileinput.js',
        '../raw/jslib/purify.js',
        '../raw/jslib/theme.js',
        '../raw/jslib/zh.js',
        //侧边菜单插件
        '../raw/jslib/slideout.js',
        //一个效果插件
        '../raw/jslib/easing.js',
    ],
    css2oneLibPath: [
        '../raw/csslib/bootstrap.css',
        '../raw/csslib/ng-table.css',
        '../raw/csslib/slick.css',
        '../raw/csslib/slick-theme.css',
        '../raw/csslib/fileinput.css',
        // '../raw/csslib/AdminLTE.css',
        '../raw/csslib/bootstrap-datepicker3.css',

    ],
    css2oneSrcPath: ['../view/0cssSrc/*.css'],
    css2onePath: '../view/css/',
    appJsPath: "../view/js/*.js"
}
var taskNames = {
    watch: 'watch',
    many2one: 'many2one',
    replaceContent: 'replaceContent'
}

var optionsForReplace = {
    beginToReplace: '{"##begin##"})',
    beginAfterReplace: '{',
    endToReplace: '{"##end##"}',
    endAfterReplace: '})'
}
var optionsForPlumber = {
    errorHandler: true
}
function many2one(jsPath, onePath, fileName) {
    gulpModules.gulp.src(jsPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpConcat(fileName))
        .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
        .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
        .pipe(gulpModules.gulp.dest(onePath))
}
// gulpModules.gulp.task(taskNames.replaceContent, function () {
//     console.log('replaceContent')
//     gulpModules.gulp.src(processPath.appJsPath)
//         .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
//         .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
//         .pipe(gulpModules.gulp.dest("../view/js/"))
// })

gulpModules.gulp.task(taskNames.many2one, function () {
    many2one(processPath.js2oneSrcPath, processPath.js2onePath, 'app.js')//把多个js源码合成一个app.js
    many2one(processPath.js2oneLibPath, processPath.js2onePath, 'lib.js')//把多个js库合成一个lib.js
    many2one(processPath.css2oneSrcPath, processPath.css2onePath, 'app.css')//把多个css源码合成一个
    many2one(processPath.css2oneLibPath, processPath.css2onePath, 'lib.css')//把多个css库合成一个
})

gulpModules.gulp.task(taskNames.watch, function () {
    //监视源码是否有修改，并执行相应的任务
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath, processPath.css2oneSrcPath, processPath.css2oneLibPath], [taskNames.many2one]);//js2one流程
    //如果是监听多个文件，使用一个数组就可以
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath, processPath.css2oneSrcPath, processPath.css2oneLibPath], function (event) {
        //console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
        gulpModules.gulpUtil.log("File-->" + event.path);
        gulpModules.gulpUtil.log("Event:" + event.type);
    });
})
gulpModules.gulp.task('default', [taskNames.watch, taskNames.many2one]);

