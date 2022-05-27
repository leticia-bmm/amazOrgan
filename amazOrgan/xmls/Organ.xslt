<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />


<xsl:template match="/">
   <html>
   <p><h1>WELCOME TO <b>AMAZORGAN!</b> </h1></p>
   <a href="http://www.ont.es/informacion/Paginas/Donaci%c3%b3n.aspx"> <p> HERE IS SOME INFO ABOUT DONATION! ;) </p> </a>
   <img src="https://www.presentationmagazine.com/newimages/organ.jpg"/>
   <p>Here is the info of the organs that are in our database</p>
   <table border = '2'>
      <th>Name</th>
      <th>Size</th>
      <th>Available</th>
      <xsl:for-each select="Organ-List/organs">
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><i><xsl:value-of select="@size" /></i></td>
            <td><i><xsl:value-of select="@available" /></i></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>
