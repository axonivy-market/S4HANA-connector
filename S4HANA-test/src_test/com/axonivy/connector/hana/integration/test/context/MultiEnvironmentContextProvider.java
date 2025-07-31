package com.axonivy.connector.hana.integration.test.context;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import com.axonivy.connector.hana.integration.test.constants.S4HanaTestConstants;

public class MultiEnvironmentContextProvider implements TestTemplateInvocationContextProvider {

	@Override
	public boolean supportsTestTemplate(ExtensionContext context) {
		return true;
	}

	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		String testEnv = System.getProperty(S4HanaTestConstants.END_TO_END_TESTING_ENVIRONMENT_KEY);
		return switch (testEnv) {
		case S4HanaTestConstants.END_TO_END_TESTING_ENVIRONMENT_VALUE ->
			Stream.of(new TestEnvironmentInvocationContext(S4HanaTestConstants.REAL_CALL_CONTEXT_DISPLAY_NAME));
		default ->
			Stream.of(new TestEnvironmentInvocationContext(S4HanaTestConstants.MOCK_SERVER_CONTEXT_DISPLAY_NAME));
		};
	}
}
