<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:x="http://it.nure.ua/matsuytskij/xml/"
                xmlns:even="http://it.nure.ua/matsuytskij/xml/event"
                xmlns:plac="http://it.nure.ua/matsuytskij/xml/place">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Event Information</h2>
                <p>ID: <xsl:value-of select="/x:Event/even:id_event"/></p>
                <p>Name: <xsl:value-of select="/x:Event/even:name"/></p>
                <p>Date: <xsl:value-of select="/x:Event/even:date"/></p>
                <p>Place: <xsl:value-of select="/x:Event/even:place/plac:online"/></p>
                <p>Description: <xsl:value-of select="/x:Event/even:description"/></p>
                <p>Pinned: <xsl:value-of select="/x:Event/even:pinned"/></p>
                <p>Time: <xsl:value-of select="/x:Event/even:time"/></p>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
