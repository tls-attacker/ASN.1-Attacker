# Asn1Tool

![licence](https://img.shields.io/badge/License-Apachev2-brightgreen.svg)
[![Build Status](http://hydrogen.cloud.nds.rub.de/buildStatus/icon.svg?job=ASN.1-Tool)](http://hydrogen.cloud.nds.rub.de/job/ASN.1-Tool/)

ASN.1 Tool is an open-source framework for generating arbitrary ASN.1 structures. The tool also provides mechanisms to define modifications of original ASN.1 values. Resulting binary ASN.1 structures can then be used for further processing in other tools.

The tool is not intended to be used directly, but by other software projects as a library.

#Installation

In order to compile and use ASN.1-Tool, you need to have Java and Maven installed. On Ubuntu you can install Maven by
 running:
```bash
$ sudo apt-get install maven
```
ASN.1-Tool currently needs Java JDK 11 to run. If you have the correct Java version you can install
 ASN.1-Tool as follows.
```bash
$ git clone https://github.com/tls-attacker/ASN.1-Tool.git
$ cd ASN.1-Tool
$ mvn clean install
```

If you want to use this project as a dependency, you do not have to compile it yourself and can include it in your pom
.xml as follows.

```xml
<dependency>
    <groupId>de.rub.nds</groupId>
    <artifactId>Asn1Tool</artifactId>
    <version>1.3.0</version>
</dependency>
```

# Acknowledgements
The framework was initially developed by Nils Kafka (nils.kafka@ruhr-uni-bochum.de) during his master thesis. 

# Projects
This framework is used in future versions of TLS-Attacker (https://github.com/tls-attacker/TLS-Attacker-Development/)
