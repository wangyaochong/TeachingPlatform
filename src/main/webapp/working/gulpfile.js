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
    js2oneSrcPath: ['../raw/js/begin.js', '../raw/js/state/*.js', '../raw/js/end.js', '../view/directive_controller_Js/*.js'],
    js2onePath: '../view/js/',
    js2oneLibPath: ['../raw/jslib/jquery-3.1.0.js', '../raw/jslib/bootstrap.js',
        '../raw/jslib/angular.js','../raw/jslib/ng-table.js','../raw/jslib/angular-ui-router.js'],
    css2oneLibPath: ['../raw/csslib/bootstrap.min.css','../raw/csslib/ng-table.css'],
    css2oneSrcPath:['../raw/css/*.css'],
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
    many2one(processPath.js2oneSrcPath, processPath.js2onePath, 'app.js')//把多个源码js合成一个app.js
    many2one(processPath.js2oneLibPath, processPath.js2onePath, 'lib.js')//把多个库合成一个lib.js
    many2one(processPath.css2oneLibPath, processPath.css2onePath, 'lib.css')//把多个css库合成一个
    many2one(processPath.css2oneSrcPath,processPath.css2onePath,'app.css')
})

gulpModules.gulp.task(taskNames.watch, function () {
    //监视jade路径下的文件是否有修改
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath], [taskNames.many2one]);//js2one流程
    //如果是监听多个文件，使用一个数组就可以
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.appJsPath], function (event) {
        //console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
        gulpModules.gulpUtil.log("File-->" + event.path);
        gulpModules.gulpUtil.log("Event:" + event.type);
    });
})
gulpModules.gulp.task('default', [taskNames.watch, taskNames.many2one]);

