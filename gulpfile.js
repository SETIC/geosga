var gulp = require('gulp'),
    clean = require('gulp-clean'),
    uglify = require('gulp-uglify'),
    jshint = require('gulp-jshint'),
    concat = require('gulp-concat'),
    stylish = require('jshint-stylish'),
    jade = require('gulp-jade'),
    sass = require('gulp-sass'),
    es = require('event-stream'),
    webserver = require('gulp-webserver'),
    sourcemaps = require('gulp-sourcemaps');

gulp.task('clean-views', function(){
    return es.merge([
        gulp.src('dist/views/**/*.html')
    ])
    .pipe(clean());
});

gulp.task('clean-js', function(){
    return gulp.src('dist/js/**/*.js')
        .pipe(clean());
});

gulp.task('clean-css', function(){
    return gulp.src('dist/css/**/*.css')
        .pipe(clean());
});

gulp.task('move-js', ['clean-js'], function(){
    return es.merge([
        gulp.src('dev/js/**/*.js')
            .pipe(sourcemaps.init())
                .pipe(uglify())
                .pipe(concat('app.min.js'))
            .pipe(sourcemaps.write('.')),

        gulp.src(['bower_components/angular/angular.min.js',
                  'bower_components/angular-cookies/angular-cookies.min.js',
                  'bower_components/angular-locale/angular-locale_pt-br.js',
                  'bower_components/angular-route/angular-route.min.js',
                  'bower_components/angular-file-model/angular-file-model.js',
                  'bower_components/angular-input-masks/angular-input-masks-standalone.min.js',
                  'bower_components/angular-input-masks/angular-input-masks.min.js',
                  'bower_components/angular-input-masks/angular-input-masks.br.min.js',
                  'bower_components/angular-checklist-model/checklist-model.js'
              ])
            .pipe(concat('vendor.min.js')),

        gulp.src([
                  'bower_components/remarkable-bootstrap-notify/bootstrap-notify.min.js'
                ])
            .pipe(concat('jquery-plugins.js'))
    ])
    .pipe(gulp.dest('dist/js'));
});


gulp.task('sass', ['clean-css'], function(){
    return es.merge([
            gulp.src('dev/styles/**/*.sass')
                .pipe(sass().on('error', sass.logError))
                .pipe(concat('styles.css')),

            gulp.src(['bower_components/animate.css/animate.min.css'])
                .pipe(concat('vendor-styles.css'))
            ])
            .pipe(gulp.dest('dist/css'));
});

gulp.task('jade', ['clean-views'], function(){
    return es.merge([
        gulp.src('dev/views/**/*.jade')
            .pipe(jade())
            .pipe(gulp.dest('dist/views')),

        gulp.src('dev/includes/**/*.jade')
            .pipe(jade())
            .pipe(gulp.dest('dist/includes'))
    ]);
});

gulp.task('server', function(){
    gulp.src('')
        .pipe(webserver({
            livereload: true,
            directoryListing: false,
            open: false
        }));
});

gulp.task("default", ['move-js', 'server', 'jade', 'sass'], function(){
    gulp.watch('dev/js/**/*.js', ['move-js']);
    gulp.watch('dev/views/**/*.jade', ['jade']);
    gulp.watch('dev/includes/**/*.jade', ['jade']);
    gulp.watch('dev/styles/**/*.sass', ['sass']);
});
