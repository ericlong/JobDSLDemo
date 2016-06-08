folder('FreestyleJob')
folder('quidryan')
folder('Variable')
folder('Loops')
folder('Pipeline')

job('FreestyleJob/DSL-Tutorial-1-Test') {
    scm {
        git('git://github.com/jgritman/aws-sdk-test.git')
    }
    steps {
        maven('-e clean test')
    }
}

/*

def project = 'quidryan/aws-sdk-test'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
    def branchName = it.name
    def jobName = "${project}-${branchName}"
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
def jobName = 'Variable/example'

job(jobName) {

}

// Loops
def giturl = 'https://github.com/quidryan/aws-sdk-test.git'
for(i in 0..10){
  job("Loops/DSL-Tutorial-1-Test-${i}") {
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

pipelineJob('Pipeline/Borat') {
    definition {
        cpsScm {
            scm {
                github('jenkins-inc/borat')
            }
        }
    }
}

pipelineJob('Pipeline/securitay') {
    definition {
        cpsScm {
            scm {
                github('jenkins-inc/securitay')
            }
        }
    }
}

pipelineJob('Pipeline/CloudBeers') {
    definition {
        cpsScm {
            scm {
                github('cloudbeers/multibranch-demo')
            }
        }
    }
}

pipelineJob('Pipeline/SimpleBuild') {
    definition {
        cpsScm {
            scm {
                github('jenkinsci/simple-build-for-pipeline-plugin')
            }
        }
    }
}

pipelineJob('Pipeline/Eric') {
    definition {
        cpsScm {
            scm {
                github('ericlong/JobDSLDemo')
            }
        }
    }
}

//Reuse job definitions

[
    [folder: 'FreestyleJob', jobname: 'Reused1', repo: 'ericlong/JobDSLDemo'],
    [folder: 'quidryan', jobname: 'Reused2', repo: 'ericlong/JobDSLDemo'],
    [folder: 'Variable', jobname: 'Reused3', repo: 'ericlong/JobDSLDemo'],
    [folder: 'Loops', jobname: 'Reused4', repo: 'ericlong/JobDSLDemo'],
    [folder: 'Pipeline', jobname: 'Reused5', repo: 'ericlong/JobDSLDemo'],
].each { Map config ->
  
  pipelineJob("${config.folder}/${config.jobname}") {
        definition {
            cpsScm {
                scm {
                  github(config.repo)
                }
            }
        }
    }
}


//MultiBranch

multibranchPipelineJob('Multibranch'){
    branchSources {
        github {
            repoOwner('kishorebhatia')
            repository('pipeline-as-code-demo')
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(20)
        }
    }
}

*/
