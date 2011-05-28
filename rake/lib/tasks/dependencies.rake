
desc 'Downloads dependencies for the project'
task :dependencies => [ 'dependencies:default' ]

namespace :dependencies do

  task :default => [ :junit, :checkstyle ]

  TEST_LIB_PATH = File.join(ROOT_PATH, 'test', 'lib')
  
  directory TEST_LIB_PATH

  task :junit => TEST_LIB_PATH do
    versioned_jar = 'junit-4.8.2.jar'
    versioned_jar_file = File.join(TEST_LIB_PATH, versioned_jar)
    unless File.exist?(versioned_jar_file)
      FileList[File.join(TEST_LIB_PATH, 'junit*.jar')].each do |f|
        rm f
      end
      download_url = 'https://github.com/KentBeck/junit/downloads/'
      download_url << versioned_jar
      command = [ 'curl',
        '--location',
        download_url,
        '>',
        versioned_jar_file ]
      sh command.join(' ')
    end
  end

  task :checkstyle => TEST_LIB_PATH do
    versioned_jar = 'checkstyle-5.3-all.jar'
    versioned_jar_file = File.join(TEST_LIB_PATH, versioned_jar)
    unless File.exist?(versioned_jar_file)
      FileList[File.join(TEST_LIB_PATH, 'checkstyle*.jar')].each do |f|
        rm f
      end
      download_url = 'http://sourceforge.net'
      download_url << '/projects/checkstyle/files/checkstyle/5.3'
      download_url << '/checkstyle-5.3-bin.tar.gz'
      command = [ 'curl',
        '--location',
        download_url,
        '|',
        "tar Oxf - checkstyle-5.3/#{versioned_jar}",
        '>',
        versioned_jar_file ]
      sh command.join(' ')
    end
  end
end
