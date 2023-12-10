<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="http://www.nure.ua/courses/"
                xmlns:tns="http://www.nure.ua/course/"
                xmlns:tns1="http://www.nure.ua/entities/"
                exclude-result-prefixes="tns tns1">
    <xsl:param name="filterPrice" select="100.0"/>
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    font-size: 14px;
                    }
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-bottom: 20px;
                    }
                    th, td {
                    border: 1px solid black;
                    padding: 10px;
                    text-align: left;
                    }
                    th {
                    background-color: #f0f0f0;
                    font-weight: bold;
                    }
                    .courseName, .description, .courseType, .language, .curatorId, .price,
                    .currency, .courseType {
                    font-weight: normal;
                    }
                </style>
            </head>
            <body>
                <h1>Курси (ціна менша за <xsl:value-of select="$filterPrice"/>)</h1>
                <table>
                    <tr>
                        <th>Назва курсу</th>
                        <th>Опис</th>
                        <th>Тип курсу</th>
                        <th>Мова</th>
                        <th>Куратор ID</th>
                        <th>Ціна</th>
                        <th>Валюта</th>
                    </tr>
                    <xsl:apply-templates select="//p:Course">
                        <xsl:sort select="tns:CourseName"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="p:Course">
        <xsl:choose>
            <xsl:when test="tns:Price &lt; $filterPrice">
                <tr>
                    <td><span class="courseName"><xsl:value-of
                            select="tns:CourseName"/></span></td>
                    <td><span class="description"><xsl:value-of
                            select="tns:Description"/></span></td>
                    <td><span class="courseType"><xsl:value-of
                            select="tns:CourseType"/></span></td>
                    <td><span class="language"><xsl:value-of
                            select="tns:Language"/></span></td>
                    <td><span class="curatorId"><xsl:value-of
                            select="tns:CuratorId"/></span></td>
                    <td><span class="price"><xsl:value-of select="tns:Price"/></span></td>
                    <td><span class="currency"><xsl:value-of
                            select="tns:Currency"/></span></td>
                </tr>
            </xsl:when>
            <xsl:otherwise>
                <xsl:if test="tns:Language = 'English'">
                    <tr>
                        <td><span class="courseName"><xsl:value-of
                                select="tns:CourseName"/></span></td>
                        <td><span class="description"><xsl:value-of
                                select="tns:Description"/></span></td>
                        <td><span class="courseType"><xsl:value-of
                                select="tns:CourseType"/></span></td>
                        <td><span class="language"><xsl:value-of
                                select="tns:Language"/></span></td>
                        <td><span class="curatorId"><xsl:value-of
                                select="tns:CuratorId"/></span></td>
                        <td><span class="price"><xsl:value-of
                                select="tns:Price"/></span></td>
                        <td><span class="currency"><xsl:value-of
                                select="tns:Currency"/></span></td>
                    </tr>
                </xsl:if>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>