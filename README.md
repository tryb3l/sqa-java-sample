# Base JAVA WD3 TestNG 7 project.

It is a sample boilerplate project created w/ page object pattern, its already have a few test cases and those make some interactions on sample 'theinternet' web app. Test cases r executed in parallel(2 threads). It could be executed in firefox, chrome, html-unit, phantomjs. In headless, and as well as non-headless mode, and selenium grid. 


## Pre requirements:

*   JDK 13 [download](https://www.oracle.com/java/technologies/javase-jdk13-downloads.html)
*   Maven [download](https://maven.apache.org/download.cgi)
*   Allure2 [Installation](https://docs.qameta.io/allure/#_installing_a_commandline)
*   Firefox [stable-build](https://www.mozilla.org/en-US/firefox/download/thanks/)
*   Chrome [stable-build](https://www.google.com/chrome/)

## Exec instructions:

    * Use make instructions to execute tasks. Those are:
    - **make test** - it would execute predefined test cases in test suite xml files path to those files: src/test/resources/TestSuites/
    - **make report** - it will serve graphic test reports for previously executed tests w/ various graphs, metrics, trends and w/ useful info for project staff.
