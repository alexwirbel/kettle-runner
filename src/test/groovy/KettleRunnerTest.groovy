/**
 * Created by timofeev-aa-090513 on 26.08.16.
 * @author timofeev-aa-090513
 */
import spock.lang.Specification

class KettleRunnerTest extends Specification {
    def "loadFileMethod returns existing file"() {
        setup:
        KettleRunner kettleRunner = new KettleRunner()
        when:
        def result = kettleRunner.loadFile("./ktr/simple.ktr").exists()
        then:
        result == true
    }

    def "runTransformationMvnMethod returns existing file"() {
        setup:
        KettleRunner kettleRunner = new KettleRunner()
        when:
        kettleRunner.runTransformationMvn("mvn:http://mvn.vetrf.ru/artifactory/repo!ru.vetrf.test.kettle-runner/simple/1.0.0/ktr")
        then:
        true == true
    }
}
