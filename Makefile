JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
	Connection.java \
	Router.java \
	WeightedGraph.java \
	dvrouter.java \
	lsrouter.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
