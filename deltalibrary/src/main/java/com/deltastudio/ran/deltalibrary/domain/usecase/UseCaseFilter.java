package com.deltastudio.ran.deltalibrary.domain.usecase;

import com.deltastudio.ran.deltalibrary.domain.annotation.UseCaseMethod;
import com.deltastudio.ran.deltalibrary.utils.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class UseCaseFilter {

    static Method filter(Object useCase, String useCaseName, Object[] args) {
        List<Method> methodsFiltered = getAnnotatedUseCaseMethods(useCase);
        if (methodsFiltered.isEmpty()) {
            throw new IllegalArgumentException("This object doesn't contain any use case to execute."
                    + "Did you forget to add the @UseCaseMethod annotation?");
        }

        methodsFiltered = getMethodMatchingName(useCaseName, methodsFiltered);
        if (methodsFiltered.isEmpty()) {
            throw new IllegalArgumentException("The target doesn't contain any use case with this name."
                    + "Did you forget to add the @UseCaseMethod annotation?");
        }

        methodsFiltered = getMethodMatchingArguments(args, methodsFiltered);
        if (methodsFiltered.isEmpty()) {
            throw new IllegalArgumentException("The target doesn't contain any use case with those args. "
                    + "Did you forget to add the @UseCaseMethod annotation?");
        }

        if (methodsFiltered.size() > 1) {
            throw new IllegalArgumentException(
                    "The target contains more than one use case with the same signature. "
                            + "You can use the 'name' property in @UseCaseMethod and invoke it with a param name");
        }

        return methodsFiltered.get(0);
    }

    static Method filterValidMethodArgs(Object[] argsToSend, Method[] methods, Class typeAnnotation) throws NoSuchMethodException {

        List<Method> methodsFiltered = new ArrayList<>();

        for (Method method : methods) {
            Annotation annotationValid = method.getAnnotation(typeAnnotation);
            if (annotationValid != null) {
                Class<?>[] parameters = method.getParameterTypes();
                if (parameters.length == argsToSend.length) {
                    if (hasValidArgumentsForReturn(parameters, argsToSend)) {
                        methodsFiltered.add(method);
                    }
                }
            }
        }

        if (methodsFiltered.isEmpty()) {
            throw new NoSuchMethodException(
                    "No public @Success-annotated method exists with this signature");
        }

        if (methodsFiltered.size() > 1) {
            throw new RuntimeException(
                    "This success has more than one method with this signature. Remove the ambiguity.");
        }

        return methodsFiltered.get(0);
    }

    private static List<Method> getAnnotatedUseCaseMethods(Object target) {
        List<Method> useCaseMethods = new ArrayList<>();

        Method[] methods = target.getClass().getMethods();
        for (Method method : methods) {
            UseCaseMethod useCaseMethodMethod = method.getAnnotation(UseCaseMethod.class);

            if (useCaseMethodMethod != null) {
                useCaseMethods.add(method);
            }
        }
        return useCaseMethods;
    }

    private static List<Method> getMethodMatchingArguments(Object[] selectedArgs,
                                                           List<Method> methodsFiltered) {

        Iterator<Method> iteratorMethods = methodsFiltered.iterator();

        while (iteratorMethods.hasNext()) {
            Method method = iteratorMethods.next();

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length == selectedArgs.length) {
                if (!hasValidArguments(parameters, selectedArgs)) {
                    iteratorMethods.remove();
                }
            } else {
                iteratorMethods.remove();
            }
        }

        return methodsFiltered;
    }

    private static boolean hasValidArguments(Class<?>[] parameters, Object[] selectedArgs) {
        for (int i = 0; i < parameters.length; i++) {
            Object argument = selectedArgs[i];
            if (argument != null) {
                Class<?> targetClass = argument.getClass();
                Class<?> parameterClass = parameters[i];
                if (!ClassUtils.canAssign(targetClass, parameterClass)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasValidArgumentsForReturn(Class<?>[] parameters, Object[] selectedArgs) {
        for (int i = 0; i < parameters.length; i++) {
            Class<?> targetClass = selectedArgs[i].getClass();
            Class<?> parameterClass = parameters[i];
            if (!ClassUtils.canAssign(parameterClass, targetClass)) {
                return false;
            }
        }
        return true;
    }

    private static List<Method> getMethodMatchingName(String nameUseCase,
                                                      List<Method> methodsFiltered) {
        if (nameUseCase == null || nameUseCase.equals("")) {
            return methodsFiltered;
        }

        Iterator<Method> iteratorMethods = methodsFiltered.iterator();
        while (iteratorMethods.hasNext()) {
            Method method = iteratorMethods.next();
            UseCaseMethod annotation = method.getAnnotation(UseCaseMethod.class);
            if (!(annotation.name().equals(nameUseCase))) {
                iteratorMethods.remove();
            }
        }

        return methodsFiltered;
    }
}
