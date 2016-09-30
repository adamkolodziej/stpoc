module.exports = function(grunt) {

  grunt.initConfig({
    less: {
      build: {
        src: 'less/style.less',
        dest: 'css/style.css'
      },
      options: {
        sourceMap: true,
        sourceMapFileInline: true
      }
    },

    watch: {
      styles: {
        files: ['less/**/*.less'],
        tasks: ['less']
      }
    },
    browserSync: {
      dev: {
        bsFiles: {
          src: [
            "css/*.css"
          ]
        },
        options: {
          watchTask: true,
		  proxy: "http://electronics.lh:9001/facebook"
        }
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-browser-sync');


  grunt.registerTask('default', ['browserSync', 'watch']);
};
