<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />


<xsl:template match="/">
   <html>
   <p><h1>WELCOME TO <b>AMAZORGAN!</b> </h1></p>
   <a href="http://www.ont.es/informacion/Paginas/Donaci%c3%b3n.aspx"> <p> HERE IS SOME INFO ABOUT DONATION! ;) </p> </a>
   <img src="https://www.presentationmagazine.com/newimages/organ.jpg"/>
   <p>Here is the info of the organs that are in our database</p>
   <table border = '3'>
      <th>Name</th>
      <th>Lifespan</th>
      <th>Size</th>
      <th>Available</th>
      <th>DNI of the donor</th>

      <xsl:for-each select="Organ-List/organs">
            <tr>
            <td><i><xsl:value-of select="type_organ/@name" /></i></td>
            <td><i><xsl:value-of select="type_organ/@lifespan" /></i></td>
            <td><i><xsl:value-of select="@size" /></i></td>
            <td><i><xsl:value-of select="@available" /></i></td>
            <td><i><xsl:value-of select="donor/@dni" /></i></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>
