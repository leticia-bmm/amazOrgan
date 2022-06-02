<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />


<xsl:template match="/">
   <html>
   <p><h1>WELCOME TO <b>AMAZORGAN!</b> </h1></p>
   <a href="http://www.ont.es/informacion/Paginas/Donaci%c3%b3n.aspx"> <p> HERE IS SOME INFO ABOUT DONATION! ;) </p> </a>
   <img src="https://www.presentationmagazine.com/newimages/organ.jpg"/>
   <p>Here are the organs that are registered in our database</p>
   <table border = '25'>
      <th><i>Name</i></th>
      <th><i>Lifespan</i></th>
      <th><i>Size</i></th>
      <th><i>Available</i></th>
      <th><i>DNI of the donor</i></th>
      <xsl:for-each select="Organ-List/organs">
            <tr>
            <td><xsl:value-of select="type_organ/@name" /></td>
            <td><i><xsl:value-of select="type_organ/@lifespan" /></i></td>
            <td><i><xsl:value-of select="@size" /></i></td>
            <td><i><xsl:value-of select="@available" /></i></td>
            <td><xsl:value-of select="donor/@dni" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>
