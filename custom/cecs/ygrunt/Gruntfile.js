module.exports = function (grunt) {
    var paths = {
        vendors: 'assets/vendors/',
        accelerator: '../yacceleratorstorefront/web/webroot/_ui/desktop/common/'
    };
    grunt.initConfig({
        notify_hooks: {
            options: {
                enabled: true,
                max_jshint_notifications: 5, // maximum number of notifications from jshint output
                title: "Tricast", // defaults to the name in package.json, or will use project directory's name
                success: false, // whether successful grunt executions should be notified automatically
                duration: 3 // the duration of notification in seconds, for `notify-send only
            }
        },
        // Process main less file to css file in build folder
        less: {
            compileCore: {
                options: {
                    strictMath: true,
                    sourceMap: true,
                    outputSourceFiles: true,
                    sourceMapURL: 'bootstrap.css.map',
                    sourceMapFilename: 'build/css/bootstrap.css.map'
                },
                src: paths.vendors + 'bootstrap/less/bootstrap.less',
                dest: 'build/css/bootstrap.css'
            },
            compileTheme: {
                options: {
                    strictMath: true,
                    sourceMap: true,
                    outputSourceFiles: true,
                    sourceMapURL: 'bootstrap-theme.css.map',
                    sourceMapFilename: 'build/css/bootstrap-theme.css.map'
                },
                src: paths.vendors + 'bootstrap/less/theme.less',
                dest: 'build/css/bootstrap-theme.css'
            },
            compileFontawesome: {
                options: {
                    strictMath: true,
                    sourceMap: true,
                    outputSourceFiles: true,
                    sourceMapURL: 'fontawesome.css.map',
                    sourceMapFilename: 'build/css/fontawesome.css.map'
                },
                src: paths.vendors + 'fontawesome/less/font-awesome.less',
                dest: 'build/css/fontawesome.css'
            },
            compileYgenerator: {
                options: {
                    strictMath: true,
                    sourceMap: true,
                    sourceMapFileInline: true
                },
                src: 'assets/less/ygrunt/ygrunt.less',
                dest: 'build/css/ygrunt.css'
            }
        },

        concat: {
            bootstrap: {
                src: [
                        paths.vendors + 'bootstrap/js/transition.js',
                        paths.vendors + 'bootstrap/js/alert.js',
                        paths.vendors + 'bootstrap/js/button.js',
                        paths.vendors + 'bootstrap/js/carousel.js',
                        paths.vendors + 'bootstrap/js/collapse.js',
                        paths.vendors + 'bootstrap/js/dropdown.js',
                        paths.vendors + 'bootstrap/js/modal.js',
                        paths.vendors + 'bootstrap/js/tooltip.js',
                        paths.vendors + 'bootstrap/js/popover.js',
                        paths.vendors + 'bootstrap/js/scrollspy.js',
                        paths.vendors + 'bootstrap/js/tab.js',
                        paths.vendors + 'bootstrap/js/affix.js',
                        paths.vendors + 'bootstrap/js/slick.js'

                ],
                dest: 'build/js/bootstrap.js'
            },

            plugins: {
                src: [
                    paths.vendors + 'angular-bootstrap/ui-bootstrap-tpls.min.js',
                    paths.vendors + 'angular-sanitize/angular-sanitize.min.js',
                    paths.vendors + 'videogular/videogular.min.js',
                    paths.vendors + 'videogular-controls/vg-controls.min.js',
                    paths.vendors + 'videogular-poster/vg-poster.min.js',
                    'assets/js/plugins/*.js'
                ],
                dest: 'build/js/plugins.js'
            },
            ygrunt: {
                src: 'assets/js/acc/*.js',
                dest:'build/js/ygrunt-acc.js'
            },
            tricastNG:{
                options: {
                    // Replace all 'use strict' statements in the code with a single one at the top
                    banner: "'use strict';\n",
                    process: function(src, filepath) {
                        return '// Source: ' + filepath + '\n' +
                            src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');
                    }
                },
                src: 'assets/js/angular/**/*.js',
                dest:'build/js/tricast-ng.js'
            }
        },

        //Clean build directory
        clean: {
            build: ['build/']
        },

        //Merge html files & and move to build folder
        includes: {
            compileHtml: {
                cwd: 'pages',
                src: '*.html',
                dest: 'build/',
                options: {
                    silent: true,
                    includePath: 'include'
                }
            }
        },

        //Copy assets from src to build folder
        copy: {
            main: {
                files: [
                    {expand: true, cwd: paths.vendors+'bootstrap/fonts', src: ['**'], dest: 'build/fonts'},
                    {expand: true, cwd: paths.vendors+'fontawesome/fonts', src: ['**'], dest: 'build/fonts'},
                    {expand: true, cwd: 'assets/fonts', src: ['**'], dest: 'build/fonts'},
                    {expand: true, cwd: 'assets/images', src: ['**'], dest: 'build/images'},
                    {expand: true, cwd: 'assets/vendors/jquery/dist', src: ['**'], dest: 'build/js'}
                ]
            },
            acceleratorCss: {
                files: [
                    {
                        expand: true,
                        cwd: 'build/css/',
                        src: ['*.css','*.map'],
                        dest: paths.accelerator+'css'
                    }
                ]
            }
            ,
            acceleratorJs: {
                files: [
                    {
                        expand: true,
                        cwd: 'build/js/',
                        src: ['*.js','*.map'],
                        dest: paths.accelerator+'js/tricast'
                    }
                ]
            },
            acceleratorStatic:{
                files: [
                    {
                        expand: true,
                        cwd: 'build/fonts/',
                        src: ['**'],
                        dest: paths.accelerator+'fonts'
                    },
                    {
                        expand: true,
                        cwd: 'build/images/',
                        src: ['**'],
                        dest: paths.accelerator+'images'
                    }
                ]
            }


        },

        notify: {
            watchJs: {
                options: {
                    title: 'Task Complete',  // optional
                    message: 'JS refreshed! Reload your page!' //required
                }
            },
            watchLess: {
                options: {
                    title: 'Task Complete',  // optional
                    message: 'Less and Copy Finished!' //required
                }
            },
            watchHtml: {
                options: {
                    title: 'Task Complete',  // optional
                    message: 'HTML build Finished! Wait for auto refresh!' //required
                }
            },
            main: {
                options: {
                    title: 'Build Task Complete',  // optional
                    message: 'All tasks done!' //required
                }
            }

        },

        //Watch changes in files
        watch: {
            // less files
            styles: {
                files: ['assets/**/*.less'],
                tasks: ['less-compile']

            },
            //html files
            htmls: {
                files: ['include/*.html', 'pages/*.html'],
                tasks: ['compileHtml']
            },
            js: {
                files: 'assets/**/*.js',
                tasks: ['js-compile'],
                options: {
                    livereload: false
                }
            }
//            //refresh browser after any change in build folder
//            livereload: {
//                options: {
//                    livereload: true
//                },
//                files: ['build/**/*']
//            }
        },
        browserSync: {
            default_options: {
                bsFiles: {
                    src: [
                        paths.accelerator + "js/*.js",
                        paths.accelerator + "css/*.css"
                    ]
                },
                options: {
                    watchTask: true,
                    proxy: "https://tricast.lh:9002/yacceleratorstorefront"
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-includes');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-browser-sync');
    grunt.loadNpmTasks('grunt-notify');

    grunt.registerTask('compileHtml', ['includes:compileHtml','notify:watchHtml']);
    grunt.registerTask('less-compile', ['less:compileCore', 'less:compileTheme','less:compileFontawesome', 'less:compileYgenerator','copy:acceleratorCss', 'notify:watchLess']);
    grunt.registerTask('js-compile', ['concat','copy:acceleratorJs','notify:watchJs']);
    grunt.registerTask('copy-accelerator', ['copy:acceleratorCss', 'copy:acceleratorJs', 'copy:acceleratorStatic']);

    grunt.registerTask('build', ['clean', 'less-compile', 'js-compile', 'compileHtml', 'copy:main','copy-accelerator', 'notify:main']);

    grunt.registerTask('default', ['browserSync','watch']);
    grunt.registerTask('yGrunt', ['default']);
};
