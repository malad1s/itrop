<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:x="http://it.nure.ua/matsuytskij/xml/"
                xmlns:even="http://it.nure.ua/matsuytskij/xml/event"
                xmlns:plac="http://it.nure.ua/matsuytskij/xml/place">

    <xsl:template match="/">
        <html>
            <head>
                <title>Events List</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    }
                    h1 {
                    font-size: 24px;
                    font-weight: bold;
                    }
                    table {
                    border-collapse: collapse;
                    width: 100%;
                    margin-top: 20px;
                    }
                    th, td {
                    border: 1px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                    }
                    th {
                    background-color: #f2f2f2;
                    }
                </style>
            </head>
            <body>
                <h1>Events List</h1>
                <form action="" method="get">
                    <label>Filter by Place:
                        <select name="eventPlace">
                            <option value="online" selected="selected">Online</option>
                            <option value="offline">Offline</option>
                        </select>
                        <input type="submit" value="Apply Filter" />
                    </label>
                </form>
                <p>Available Places:</p>
                <ul>
                    <li>Online</li>
                    <li>Offline</li>
                </ul>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Organizer</th>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Place</th>
                        <th>Description</th>
                        <th>Time</th>
                    </tr>
                    <!-- Використовуємо xsl:variable для зберігання значення фільтра -->
                    <xsl:apply-templates select="x:Events/x:event"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="x:event">
        <tr>
            <td><xsl:value-of select="even:name"/></td>
            <td><xsl:value-of select="@type"/></td>
            <td><xsl:value-of select="@organizer"/></td>
            <td><xsl:value-of select="even:id_event"/></td>
            <td><xsl:value-of select="even:date"/></td>
            <td>
                <xsl:choose>
                    <xsl:when test="even:place/plac:online">
                        <xsl:value-of select="even:place/plac:online"/>
                    </xsl:when>
                    <xsl:when test="even:place/plac:offline">
                        <xsl:value-of select="even:place/plac:offline"/>
                    </xsl:when>
                    <xsl:otherwise>Unknown</xsl:otherwise>
                </xsl:choose>
            </td>
            <td><xsl:value-of select="even:description"/></td>
            <td><xsl:value-of select="even:time"/></td>
        </tr>
    </xsl:template>


</xsl:stylesheet>

