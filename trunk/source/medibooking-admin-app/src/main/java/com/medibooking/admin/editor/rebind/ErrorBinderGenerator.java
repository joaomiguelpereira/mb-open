package com.medibooking.admin.editor.rebind;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;

import com.google.gwt.core.ext.typeinfo.TypeOracle;

import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import com.medibooking.admin.client.view.editor.ErrorBinder;
import com.medibooking.admin.client.view.widget.Errorable;

public class ErrorBinderGenerator extends Generator {

	private String implPackageName;

	private String implTypeName;
	private JClassType parameterizedType;

	public String generate(TreeLogger logger, GeneratorContext context,
			String requestedClass) throws UnableToCompleteException {

		TypeOracle typeOracle = context.getTypeOracle();

		JClassType objectType = typeOracle.findType(requestedClass);
		if (objectType == null) {
			logger.log(TreeLogger.ERROR, "Could not find type: "
					+ requestedClass);
			throw new UnableToCompleteException();
		}

		logger.log(
				TreeLogger.INFO,
				"objectType is: "
						+ objectType.getParameterizedQualifiedSourceName());
		implTypeName = objectType.getSimpleSourceName() + "Impl";

		implPackageName = objectType.getPackage().getName();

		JClassType[] implementedTypes = objectType.getImplementedInterfaces();

		// Can only implement one interface
		if (implementedTypes == null
				|| implementedTypes.length != 1
				|| !implementedTypes[0].getQualifiedSourceName().equals(
						ErrorBinder.class.getName())) {
			logger.log(TreeLogger.ERROR, "The type: " + requestedClass
					+ " Must implement only one interface: "
					+ ErrorBinder.class.getName());
			throw new UnableToCompleteException();
		}

		// Get parameterized type
		JParameterizedType parameterType = implementedTypes[0]
				.isParameterized();
		if (parameterType == null) {
			logger.log(TreeLogger.ERROR, "The type: " + requestedClass
					+ " Must implement only one parameterized interface: "
					+ ErrorBinder.class.getName());
			throw new UnableToCompleteException();
		}

		if (parameterType.getTypeArgs() == null

		|| parameterType.getTypeArgs().length != 1) {
			logger.log(TreeLogger.ERROR, "The type: " + requestedClass
					+ " Must implement only one parameterized interface: "
					+ ErrorBinder.class.getName() + " with only onde Parameter");
			throw new UnableToCompleteException();

		}

		parameterizedType = parameterType.getTypeArgs()[0];

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
				implPackageName, implTypeName);

		composerFactory.addImport(Map.class.getCanonicalName());
		composerFactory.addImport(List.class.getCanonicalName());
		composerFactory.addImplementedInterface(objectType
				.getQualifiedSourceName());

		PrintWriter printWriter = context.tryCreate(logger, implPackageName,
				implTypeName);
		if (printWriter != null) {

			SourceWriter sourceWriter = composerFactory.createSourceWriter(
					context, printWriter);

			composeBindErrorsMethod(logger, sourceWriter, typeOracle);
			sourceWriter.commit(logger);

		}
		return implPackageName + "." + implTypeName;
	}

	private void composeBindErrorsMethod(TreeLogger logger,
			SourceWriter sourceWriter, TypeOracle typeOracle) {

		sourceWriter.println("public void bindErrors("
				+ parameterizedType.getQualifiedSourceName()
				+ " object, Map<String, List<String>> errors) {");

		// Get the fields declared in the parameterized type
		JField[] fields = parameterizedType.getFields();
		for (JField field : fields) {

			JClassType classType = field.getType().isClass();
			if (classType != null) {

				JClassType erroableType = typeOracle.findType(Errorable.class
						.getName());

				if (classType.isAssignableTo(erroableType)) {
					
					sourceWriter.println("object." + field.getName()+".clearErrors();");
					
					sourceWriter.println("if (errors.containsKey(\""
							+ field.getName() + "\")){");
					sourceWriter.println("object." + field.getName()
							+ ".setErrors(errors.get(\"" + field.getName()
							+ "\"));");
					sourceWriter.println("}");

				}
			}

		}
		sourceWriter.println("}");

	}
}
