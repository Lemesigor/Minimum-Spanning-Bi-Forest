all: PBGM.jar

PBGM.class: PBGM.java
	javac $^

PBGM.jar: PBGM.class
	jar -cfe $@ PBGM $^

clean:
	$(RM) *.class && $(RM) *.jar
