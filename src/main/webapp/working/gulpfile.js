var gulpModules={
    gulp:require('gulp'),
    gulpUtil:require('gulp-util'),
    gulpJade:require('gulp-pug'),
    gulpConcat:require('gulp-concat'),
    gulpJshint:require('gulp-jshint'),
    gulpReplace:require('gulp-replace'),
    gulpHtml2jade:require('gulp-html2jade')
}
var processPath={
    jadePath:"../raw/jade/*.jade",
    jade2htmlPath:'../view/html/',
    jsPath:'../raw/js/*.js',
    js2onePath:'../view/js/',
    html2jadeHtmlPath:'../view/html2jade/*.html',
    html2jadeJadePath:'../raw/jade/'
}
var taskNames={
    jade2html:'jade2html',
    watch:'watch',
    js2one:'js2one',
    html2jade:'html2jade'
}
var optionsForJade={
    pretty:true
}
var optionsForReplace={
    toReplace:'hhhh',
    afterReplace:"script(src='view/js/app.js')"
}
gulpModules.gulp.task(taskNames.html2jade,function () {
    return gulpModules.gulp.src(processPath.html2jadeHtmlPath)
        .pipe(gulpModules.gulpHtml2jade())
        .pipe(gulpModules.gulp.dest(processPath.html2jadeJadePath))
})
gulpModules.gulp.task(taskNames.jade2html, function () {
    return gulpModules.gulp.src(processPath.jadePath)
        .pipe(gulpModules.gulpReplace(optionsForReplace.toReplace,optionsForReplace.afterReplace))
        .pipe(gulpModules.gulpJade(optionsForJade))
        .pipe(gulpModules.gulp.dest(processPath.jade2htmlPath))
      
});
gulpModules.gulp.task(taskNames.js2one,function () {
    return gulpModules.gulp.src(processPath.jsPath)
        .pipe(gulpModules.gulpJshint())
        .pipe(gulpModules.gulpJshint.reporter('default'))
        .pipe(gulpModules.gulpConcat("app.js"))
        .pipe(gulpModules.gulp.dest(processPath.js2onePath))
})
gulpModules.gulp.task(taskNames.watch,function () {
    //监视jade路径下的文件是否有修改
    gulpModules.gulp.watch(processPath.html2jadeHtmlPath,[taskNames.html2jade])
    gulpModules.gulp.watch(processPath.jadePath,[taskNames.jade2html]);
    gulpModules.gulp.watch(processPath.jsPath,[taskNames.js2one]);
    //如果是监听多个文件，使用一个数组就可以
    gulpModules.gulp.watch([processPath.jadePath,processPath.jsPath,processPath.html2jadeHtmlPath],function (event) {
        //console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
        gulpModules.gulpUtil.log("File-->"+event.path);
        gulpModules.gulpUtil.log("Event:"+event.type);
    });
})
gulpModules.gulp.task('default', [taskNames.watch,taskNames.jade2html,taskNames.js2one]);

