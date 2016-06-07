job('DSL-Tutorial-1-Test') {
    scm {
        git('git://github.com/jgritman/aws-sdk-test.git')
    }
    steps {
        maven('-e clean test')
    }
}

def project = 'quidryan/aws-sdk-test'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
    def branchName = it.name
    def jobName = "${project}-${branchName}".replaceAll('/','-')
    job(jobName) {
        scm {
            git("git://github.com/${project}.git", branchName)
        }
        steps {
            maven("test -Dproject.name=${project}/${branchName}")
        }
    }
}

// Variables
def jobName = 'example'

job(jobName) {

}

// Loops
def giturl = 'https://github.com/quidryan/aws-sdk-test.git'
for(i in 0..10){
  job("DSL-Tutorial-1-Test-${i}") {
    scm {
      git(giturl)
    }
    steps {
      maven("test -Dtest.suite=${i}")
    }
  }
}

//Multi-line strings


// pipeline job

pipelineJob('DSL-Pipeline') {
    definition {
        cpsScm {
            scm {
                git('https://github.com/jenkins-inc/borat.git'){
                    createTag()
                }
            }
        }
    }
}