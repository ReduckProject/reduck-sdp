package net.reduck.sdp.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import net.reduck.sdp.annotation.Sensitive;
import net.reduck.sdp.core.jpa.SensitiveConverter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.persistence.Convert;
import java.util.Set;

/**
 * @author Gin
 * @since 2023/5/4 16:19
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = "net.reduck.sdp.annotation.Sensitive")
public class SensitiveProcessor extends AbstractProcessor {
    private Types types = null;
    private Elements elements = null;
    private Filer filer = null;
    private Messager messager = null;

    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;
    private Symtab symtab;
    private AstMojo astMojo;

    public SensitiveProcessor() {
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 判断方法应该添加什么注解
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Sensitive.class)) {

            javax.lang.model.element.Name methodName = element.getSimpleName();

            TypeElement typeElem = (TypeElement) element.getEnclosingElement();

            String typeName = typeElem.getQualifiedName().toString();
            astMojo.importIfAbsent(element, Convert.class);
            astMojo.importIfAbsent(element, SensitiveConverter.class);
            JCTree jcTree = trees.getTree(typeElem);

            jcTree.accept(new TreeTranslator() {

                @Override
                public void visitVarDef(JCTree.JCVariableDecl jcVariableDecl) {
                    List<JCTree.JCAnnotation> jcAnnotations = jcVariableDecl.mods.annotations;
                    try {
                        if (jcAnnotations != null && jcAnnotations.size() > 0) {
                            List<JCTree.JCAnnotation> nil = List.nil();
                            System.err.println(nil.toString());
                            System.err.println("===============");
                            for (JCTree.JCAnnotation jcAnnotation : jcAnnotations) {
                                if (Sensitive.class.getName().equals(jcAnnotation.getAnnotationType().type.tsym.toString())) {
                                    JCTree.JCAnnotation converterAnnotation = treeMaker.Annotation(
                                            astMojo.select(Convert.class.getName())
                                            , List.of(treeMaker.Assign(treeMaker.Ident(names.fromString("converter"))
                                                    , treeMaker.Select(treeMaker.Ident(names.fromString(SensitiveConverter.class.getSimpleName()))
                                                            , names.fromString("class")))));

                                    nil = nil.append(converterAnnotation);
                                } else {
                                    nil = nil.append(jcAnnotation);
                                }
                            }

                            jcVariableDecl.mods.annotations = nil;

                            System.err.println(nil.toString());
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                    try {
                        super.visitVarDef(jcVariableDecl);
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                }
            });
        }

        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        this.types = processingEnv.getTypeUtils();
        this.elements = processingEnv.getElementUtils();
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();

        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
        this.symtab = Symtab.instance(context);

        this.astMojo = new AstMojo(trees, treeMaker, names, symtab);
    }

}
