package com.dslplatform.json.processor;

import com.dslplatform.json.CompiledJson;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttributeInfo {
	public final String id;
	public final String name;
	public final ExecutableElement readMethod;
	public final ExecutableElement writeMethod;
	public final VariableElement field;
	public final TypeMirror type;
	public final AnnotationMirror annotation;
	public final Element element;
	public final boolean notNull;
	public final boolean mandatory;
	public final int index;
	public final String alias;
	public final boolean fullMatch;
	public final CompiledJson.TypeSignature typeSignature;
	public final TypeMirror converter;
	public final boolean isJsonObject;
	public final List<String> alternativeNames = new ArrayList<String>();
	public final String readProperty;
	public final String targetName;

	public AttributeInfo(
			String name,
			ExecutableElement readMethod,
			ExecutableElement writeMethod,
			VariableElement field,
			TypeMirror type,
			AnnotationMirror annotation,
			boolean notNull,
			boolean mandatory,
			final int index,
			String alias,
			boolean fullMatch,
			CompiledJson.TypeSignature typeSignature,
			TypeMirror converter,
			boolean isJsonObject,
			String targetName) {
		this.id = alias != null ? alias : name;
		this.name = name;
		this.readMethod = readMethod;
		this.writeMethod = writeMethod;
		this.field = field;
		this.element = field != null ? field : readMethod;
		this.type = type;
		this.annotation = annotation;
		this.notNull = notNull;
		this.mandatory = mandatory;
		this.index = index;
		this.alias = alias;
		this.fullMatch = fullMatch;
		this.typeSignature = typeSignature;
		this.converter = converter;
		this.isJsonObject = isJsonObject;
		this.targetName = targetName;
		this.readProperty = field != null ? field.getSimpleName().toString() : readMethod.getSimpleName() + "()";
	}

	public boolean isEnum(Map<String, StructInfo> structs) {
		StructInfo struct = targetName == null ? null : structs.get(targetName);
		return struct != null && struct.type == ObjectType.ENUM;
	}
}
