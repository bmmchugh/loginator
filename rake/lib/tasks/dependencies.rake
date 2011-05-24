
desc 'Downloads dependencies for the project'
task :dependencies => [ 'dependencies:default' ]

namespace :dependencies do

  task :default => [ :junit ]

  TEST_LIB_PATH = File.join(ROOT_PATH, 'test', 'lib')
  
  directory TEST_LIB_PATH

  task :junit => TEST_LIB_PATH do
    FileList[File.join(TEST_LIB_PATH, 'junit*.jar')].each do |f|
      rm f
    end
    versioned_jar = 'junit-4.8.2.jar'
    download_url = 'https://github.com/KentBeck/junit/downloads/'
    download_url << versioned_jar
    command = [ 'curl',
                '--location',
                download_url,
                '>',
                File.join(TEST_LIB_PATH, versioned_jar) ]
    sh command.join(' ')
  end
end
