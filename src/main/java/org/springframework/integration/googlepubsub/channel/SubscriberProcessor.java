package org.springframework.integration.googlepubsub.channel;

import com.google.auto.service.AutoService;
import com.google.common.collect.Sets;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

/**
 * Created by joaomartins on 5/4/17.
 */
@AutoService(Processor.class)
public class SubscriberProcessor extends AbstractProcessor {

  private Map<String, List<Element>> subscriberMap;
  private Messager messager;

  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (Element element : roundEnv.getElementsAnnotatedWith(Subscriber.class)) {
      if (element.getKind() != ElementKind.METHOD) {
        messager.printMessage(Kind.ERROR, "Subscriber can only be used in methods.", element);
        return true;
      }

      Subscriber annotation = element.getAnnotation(Subscriber.class);
      String topic = annotation.topic();

      if (topic.isEmpty()) {
        messager.printMessage(Kind.ERROR, "Topic can't be empty", element);
        return true;
      }

      if (!subscriberMap.containsKey(topic)) {
        subscriberMap.put(topic, new ArrayList<>());
      }
      subscriberMap.get(topic).add(element);
    }

    return true;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(Subscriber.class.getCanonicalName());
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_8;
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);

    messager = processingEnv.getMessager();
    subscriberMap = new HashMap<>();
  }
}
