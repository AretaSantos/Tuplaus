<!DOCTYPE html>
<html>
<body>

<h1>Tuplaus</h1>

<p>Tämä on Tuplauspeli, joka on tehty Spring boot frameworkia hyödyntäen. Ohjeet pelin käynnistykseen: </p>

<h3>Asennus</h3>

<ol>
  <li>Kopioi projekti haluamaasi kansioon:</li>
</ol>

<pre>
<code>git clone https://github.com/AretaSantos/Tuplaus.git</code>
</pre>

<p>Importoi projekti maven projektina</p>

<h2>Tietokanta</h2>

<p>Ennen koodin ajamista, vaihda <code>application.properties</code> tiedostossa seuraavat kohdat: </p>

<ol>
  <li>Luo koneellesi kansiorakenne tietokannalle ja vaihda se datasource urlin kohdalle <code>spring.datasource.url</code> </li>
</ol>

<pre>
<code>spring.datasource.url=jdbc:h2:file:/path/to/your/database/folder/mydb</code>
</pre>

<ol start="2">
  <li>Aseta haluamasi käyttäjätunnus ja salasana:</li>
</ol>

<pre>
<code>spring.datasource.username=your-username
spring.datasource.password=your-password</code>
</pre>

<p>Tietokantaa pääsee tarkastelemaan <a href="http://localhost:8080/h2-console">http://localhost:8080/h2-console</a> kirjautumalla omalla käyttäjätunnuksella ja salasanalla. Käynnitys myös luo kaksi valmista riviä Player tietokantatauluun data.spl tiedoston scriptistä.

Pelien aikaiset panokset, valinnat ja lopputulos tallentuu myös tietokantaan, ja niitä voi tarkastella kunnes pelin sammuttaa ja myös tietokanta sammuu.</p>

<h2>Projektin käynnitys</h2>

<p>Aja luokka<code>TuplausApplication</code> käynnistääksesi projektin</p>

<h2>Pelaaminen</h2>

<p>Pelien aikaiset panokset, valinnat ja lopputulos tallentuu myös tietokantaan, ja niitä voi tarkastella kunnes pelin sammuttaa ja myös tietokanta sammuu. Valitse haluamasi pelaaja id tunnisteen avulla (valmiina idt 1 ja 2), syötä panos ja arvaa seuraava kortti</p>

<h2>Peli-clientin ja pelimoottorin välinen http api</h2>

<p>Peli on rakennettu Spring boot frameworkia hyödyntäen. Peliclientin ja pelimottorin väinen yhetys toimisi contoller luokan avulla, jossa contoller ottaa clientilta vastaan erilaisia urlin enpointeja 
ja suorittaa niiden pohjalta Service luokassa totetutettua pelilogiikkaa. Tässä versiossa clientia ei ole, vaan peliä pelataan consolin kautta, joten contoller luokka ei ole käytössä. Clientin voisi tuoteuttaa 
esim html templeteilla projektin sisällä tai erillisellä react sovelluksella.</p>

<h2>Jatkokehitys</h2>

<p>Pelin jatkokehityksessä mahdollisia asioita:</p>

<ul>
  <li>Testit</li>
  <li>strong> Autentikointi ja sisäänkirjaus</li>
  <li>Pelaajan vaihto kesken pelin</li>
  <li>Frontendin luominen html templateja tai erillistä react sovellusta käyttäen.</li>
</ul>

</body>

</html>
