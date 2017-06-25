# mwe-cors-unexplained-behaviour

A [re-frame](https://github.com/Day8/re-frame) application designed to understand why a very simple code doesn't work as expected.

I'm trying to issue a `GET` on a very common address, say `https://www.gnu.org`. I'm following the README of `re-frame-http-fx` but I'm puzzled because it doesn't work. It doesn't seem to be a problem about versions because both original version of lein template or latest versions of libraries lead to the same issue:

```
XMLHttpRequest cannot load https://www.gnu.org/. The 'Access-Control-Allow-Origin' header contains the invalid value '(null)'. Origin 'http://0.0.0.0:3449' is therefore not allowed access.
34
```

Same problem for some addresses I've tested so far

```
XMLHttpRequest cannot load https://github.com/Day8. No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://0.0.0.0:3449' is therefore not allowed access. The response had HTTP status code 410.
```

When copying the request constructed by `re-frame-http-fx` in Chrome I get

```
$ curl 'https://www.gnu.org/' -H 'Origin: http://0.0.0.0:3449' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: en-GB,en;q=0.8,en-US;q=0.6,la;q=0.4' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3140.0 Safari/537.36' -H 'Accept: application/json' -H 'Referer: http://0.0.0.0:3449/' -H 'Connection: keep-alive' -H 'DNT: 1' --compressed
```

which leads to the same error:

```
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html><head>
<title>406 Not Acceptable</title>
</head><body>
<h1>Not Acceptable</h1>
<p>An appropriate representation of the requested resource / could not be found on this server.</p>
Available variants:
<ul>
<li><a href="home.ca.html">home.ca.html</a> , type text/html, language ca</li>
<li><a href="home.de.html">home.de.html</a> , type text/html, language de</li>
<li><a href="home.el.html">home.el.html</a> , type text/html, language el</li>
<li><a href="home.es.html">home.es.html</a> , type text/html, language es</li>
<li><a href="home.fa.html">home.fa.html</a> , type text/html, language fa</li>
<li><a href="home.fr.html">home.fr.html</a> , type text/html, language fr</li>
<li><a href="home.html">home.html</a> , type text/html, language en</li>
<li><a href="home.it.html">home.it.html</a> , type text/html, language it</li>
<li><a href="home.ja.html">home.ja.html</a> , type text/html, language ja</li>
<li><a href="home.ko.html">home.ko.html</a> , type text/html, language ko</li>
<li><a href="home.lt.html">home.lt.html</a> , type text/html, language lt</li>
<li><a href="home.nl.html">home.nl.html</a> , type text/html, language nl</li>
<li><a href="home.pl.html">home.pl.html</a> , type text/html, language pl</li>
<li><a href="home.ru.html">home.ru.html</a> , type text/html, language ru</li>
<li><a href="home.sq.html">home.sq.html</a> , type text/html, language sq</li>
<li><a href="home.uk.html">home.uk.html</a> , type text/html, language uk</li>
<li><a href="home.zh-cn.html">home.zh-cn.html</a> , type text/html, language zh-cn</li>
<li><a href="home.zh-tw.html">home.zh-tw.html</a> , type text/html, language zh-tw</li>
</ul>
<hr>
<address>Apache/2.4.7 Server at www.gnu.org Port 443</address>
</body></html>
```

However the simple request

```
curl 'https://www.gnu.org/'
```

doesn't result in error.

Chrome version used here is _Version 61.0.3140.0 (Official Build) canary (64-bit)_.
 
### Run application:

- with latest versions:
``` bash
git master
lein clean
lein figwheel
```

- with original template versions:
``` bash
git checkout 2481d00811f2bc17576c6ce37f5d6118f60edc4c~1
lein clean
lein figwheel
```
