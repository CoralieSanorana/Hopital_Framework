# 1.Ouvrir CMD en tant qu'administrateur

# 2.Mettre à jour JAVA_HOME au niveau système :
```bash
setx JAVA_HOME "C:\Program Files\Java\jdk-21" /M
```

# 3.S'assurer que %JAVA_HOME%\bin est au tout debut du PATH 
```bash
setx PATH "C:\Program Files\Java\jdk-21\bin;%PATH%" /M
```

# 4.Definir JRE_HOME dans tomcat 10 bin
```bash
set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set "JRE_HOME=C:\Program Files\Java\jdk-21"
```
# 5.Modifier en jsk 8
Chemin
```bah
C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot
```
```bash
setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot" /M
setx PATH "C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot\bin;%PATH%" /M
```