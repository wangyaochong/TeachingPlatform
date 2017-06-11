var gulpModules = {
    gulp: require('gulp'),
    gulpUtil: require('gulp-util'),
    gulpJade: require('gulp-jade'),
    gulpConcat: require('gulp-concat'),
    gulpJshint: require('gulp-jshint'),
    gulpReplace: require('gulp-replace'),
    gulpHtml2jade: require('gulp-html2jade'),
    gulpPlumber: require('gulp-plumber'),
    gulpNgAnnotate:require('gulp-ng-annotate'),
    gulpUglify:require('gulp-uglify'),
    gulpSass:require('gulp-sass')
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
        //angular动画插件
        '../raw/jslib/angular-animate.js',
        //angular手机触屏插件
        '../raw/jslib/angular-touch.js',
        //ui-bootstrap，angular bootstrap插件
        '../raw/jslib/ui-bootstrap.js',
        '../raw/jslib/ui-bootstrap-tpls.js',

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


        //bootstrap-select插件
        // '../raw/jslib/bootstrap-select.js',
        // '../raw/jslib/defaults-zh_CN.js',
        //angular-ui-select
        '../raw/jslib/select.js',
        //angular-ui-select依赖sanitize.js
        '../raw/jslib/angular-sanitize.js',
        //视频播放插件
        '../raw/jslib/video.js',
        '../raw/jslib/videojs-ie8.js',


        '../raw/jslib/lodash.js',


        //侧边菜单插件
        // '../raw/jslib/slideout.js',
        //一个效果插件
        // '../raw/jslib/easing.js',
    ],
    css2oneLibPath: [
        // '../raw/csslib/slick.css',
        // '../raw/csslib/slick-theme.css',
        // '../raw/csslib/AdminLTE.css',
        // '../raw/csslib/bootstrap-select.css',

        
        '../raw/csslib/bootstrap.css',
        '../raw/csslib/ng-table.css',
        '../raw/csslib/fileinput.css',
        '../raw/csslib/bootstrap-datepicker3.css',
        //ui.bootstrap.css
        '../raw/csslib/ui-bootstrap-csp.css',
        // angular-ui-select
        '../raw/csslib/select.css',
        //video.js
        '../raw/csslib/video-js.css',
    ],
    css2oneSrcPath: ['../view/0cssSrc/*.css'],
    css2onePath: '../view/css/',
    sass2cssSrcPath:['../view/0cssSrc/sass/*.scss'],
    sass2cssDestPath:'../view/0cssSrc/',
    appJsPath: "../view/js/*.js"
}
var taskNames = {
    watch: 'watch',
    many2one: 'many2one',
    sass2css:'sass2css',
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


var needCompress=false;

function sass2css(sassPath,cssPath) {
    gulpModules.gulp.src(sassPath)
        .pipe(gulpModules.gulpSass().on('error',gulpModules.gulpSass.logError))
        .pipe(gulpModules.gulp.dest(cssPath));
}
function many2oneCSS(jsPath, onePath, fileName) {
    gulpModules.gulp.src(jsPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpConcat(fileName))
        .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
        .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
        .pipe(gulpModules.gulp.dest(onePath))
}
function many2oneJS(jsPath, onePath, fileName) {
    gulpModules.gulp.src(jsPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpConcat(fileName))
        .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
        .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
        .pipe(gulpModules.gulpNgAnnotate())
        .pipe(needCompress==true?
            gulpModules.gulpUglify({mangle: {except: ['require' ,'exports' ,'module' ,'$']}}):gulpModules.gulpUtil.noop() )
        .pipe(gulpModules.gulp.dest(onePath))
}


gulpModules.gulp.task(taskNames.sass2css,function () {
    sass2css(processPath.sass2cssSrcPath,processPath.sass2cssDestPath);
})

gulpModules.gulp.task(taskNames.many2one, function () {
    many2oneJS(processPath.js2oneSrcPath, processPath.js2onePath, 'app.js')//把多个js源码合成一个app.js
    many2oneJS(processPath.js2oneLibPath, processPath.js2onePath, 'lib.js')//把多个js库合成一个lib.js
    many2oneCSS(processPath.css2oneSrcPath, processPath.css2onePath, 'app.css')//把多个css源码合成一个
    many2oneCSS(processPath.css2oneLibPath, processPath.css2onePath, 'lib.css')//把多个css库合成一个
})

gulpModules.gulp.task(taskNames.watch, function () {
    //监视源码是否有修改，并执行相应的任务
    gulpModules.gulp.watch([processPath.sass2cssSrcPath],[taskNames.sass2css]);
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath, processPath.css2oneSrcPath, processPath.css2oneLibPath], [taskNames.many2one]);//js2one流程
    //如果是监听多个文件，使用一个数组就可以
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath, processPath.css2oneSrcPath, processPath.css2oneLibPath,processPath.sass2cssSrcPath], function (event) {
        //console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
        gulpModules.gulpUtil.log("File-->" + event.path);
        gulpModules.gulpUtil.log("Event:" + event.type);
    });
})
gulpModules.gulp.task('default', [taskNames.watch, taskNames.sass2css, taskNames.many2one]);

// gulpModules.gulp.task(taskNames.replaceContent, function () {
//     console.log('replaceContent')
//     gulpModules.gulp.src(processPath.appJsPath)
//         .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
//         .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
//         .pipe(gulpModules.gulp.dest("../view/js/"))
// })