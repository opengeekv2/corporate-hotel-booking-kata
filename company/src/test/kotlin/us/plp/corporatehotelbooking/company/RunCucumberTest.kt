package us.plp.corporatehotelbooking.company

import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import org.springframework.test.context.ContextConfiguration


@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "us.plp.corporatehotelbooking.company")
class RunCucumberTest