pom.xml Ayarları
-------------------
	<modelVersion>4.0.0</modelVersion> :pom.xml konf. dosyasının uyması gereken  xml yapısının versiyondur

	<groupId>com.etstur.finance</groupId> :Projenin ait olduğu organizasyonu benzersiz şekilde tanımlayan bir degerdir.Bu degerde organizasyonun alan adının tesrden yazılmış halidir
	<artifactId>supplier-service</artifactId> :Projenin ismini tanımlar
	<version>0.0.1-SNAPSHOT</version> :Projenin versiyonudur
	<packaging>jar</packaging> :packaging tipi jar olarak tanımlanır.Eger proje içinde jsp ,jsf gibi bir teknoloji   varsa bu durumda packaging tipi "war" yapmak gerekir


	<parent> :Uygulamamızın spring bootun build sürecinde sunduğu dependence ve plugin kabiliyetlerinden yararlanabilmesi için parent proje olarak
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.1.RELEASE</version> :spring boot versiyonu
	</parent>
	<dependencies> : Projede kullanlacak spring boot dependencies ve thirt party dependencies ekliyoruz
	..
	</dependencies>
	<properties>
		<java.version>1.8</java.version> : projenin jdk 1.8 ile calışıp delenmesi gerektiğini belirtiyoruz
	</properties>

	<project..>
	 ...
	    <build> : projenin buil esnasında devreye giren maven build tanımları eklenir
	       <plugin>
             		<groupId>org.springframework.boot</groupId>
             		<artifactId>spring-boot-maven-plugin</artifactId>
           </plugin>
        </build>
    </project>
    
    
 @GetMapping(path = "/test") -->  bir kısayol görevi gören karma bir ek açıklamadır.Spring 4 sürümü ile geldi.
 @RequestMapping(value = "/test", method = RequestMethod.GET)
 @ResponseBody --> Nesnenin otomatik olarak json döndürülmesini sağlar .İstediğimiz formata cevirmek için kullanılır @Controller olursa, 
                  @RestController da buna gerek yok zaten json döndürüyor.
                 @Controller da @RequestBody verilerek nesne  jsona  döndürülür.
                 @RequestBody ile eklerseniz, spring gelen istek gövdesinin içeriğini anında parametre nesnesine dönüştürmeye çalışacaktır.
 @Controller spring mvc oluyor @RestController ise mvc
 @Controller -->'ın işi bir model nesnesi haritası oluşturmak ve bir görünüm bulmaktır(HTML + CSS + JavaScript görünümü)
                 Spring  MVC Kontrolörü olarak işaretlemek için kullanılan genel bir nottur;
                 @Component   içinde  barındırır.
 @RestController --> sadece nesneyi döndürür ve nesne verileri doğrudan JSON veya XML olarak HTTP yanıtına yazılır.Herhangi bir ön yüz beklentisi yok
                 @RestController göreceli olarak yenidir, yalnızca Spring 4.0'a eklenir, 
                 @RestController içerisinde @Controller ve @ResponseBody vardır bu ikisini barındıran kısayoldur. bu nedenle RequestBody yeniden yazmaya gerek yok
                 restControler sayesinde spring conteynir bu sınıfdan bir controller bini yaratmış olacak hemde handler metodlarına ayrı ayrı ResponseBody anatosyonu yazmakdan kurtarır.
 @RequestBody -->, istemci tarafı denetleyicimizden gönderilen JSON'a karşılık gelmesi gerekir:
 @Component -->
 @PathVariable -->
 @Table -->
 @Entity -->
 @Data -->
 CrudRepository ve JpaRepository farkı -->
 @SpringBootApplication -->
 h2 dsatabase console -->http://localhost:8080/h2-console 
 @Slf4j   
 
 @ResponseBody -->Spring mvc metod return değerini http mesaj converterlar ile uygun formatda bit tekxt mesaja cevirip istemciye döndürmekdedir
 @RequestMapping-->
 @PathVariable-->
 @RequestParam -->
 @QuesyParam -->
 @RequestBody-->
 @ResponseStatus -->http statu kodlarını response entity ile dönmek yerine ResponseStatus anatosyonu ile de calışabilirim  metod başına eklenir.exeption classlarının üstüne yazabilirr.
  @PathVariable @RequestParam arasındaki fark
 
 httpMethod ları {
     GET,
     HEAD,
     POST,
     PUT,
     PATCH,
     DELETE,
     OPTIONS,
     TRACE;
     }
     
JpaRepository ve CrudRepository arasındaki fark
java ee de repository olayı nasıl
NativeQuery 
NamedQueries   
**NamedNativeQueries**  de resultSetMapping

Hateous rest service

SEQUENCE nedir

-spring-boot-starter-test kullanınca dolaylı olarak junit proojeye dahi edilir.

-spring-boot-starter pom.xml de olduğu için validation için bişey eklemedik @Valid kullanabiliyoruz.PetClinicService ve 
PetClinicServiceImp her ikiisndede createOwner metoduna @Valid  anatasyonunu koyduk cünkü servicin interface varsa onada koymak gerekiyor.

ResponseEntity ile rest servisin hem döndüreceği deger hemde statu kodu birlikde ifade edelir.