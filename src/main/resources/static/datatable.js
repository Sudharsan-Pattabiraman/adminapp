$(document).ready(function() {
      var displayResources = $("#display-resources");

      $.ajax({
      type: "GET",
      url: "http://localhost:8080/product/list", // Using our resources.json file to serve results
      success: function(result) {
        console.log(result);
        var output =
          "<table><thead><tr><th>Product Name</th><th>Product Id</th><th>Product Points</th><th>Product Type</th><th></th></thead><tbody>";
        for (var i in result) {
          output +=
            "<tr><td>" +
            result[i].productName +
            "</td><td>" +
            result[i].productId +
            "</td><td>" +
            result[i].productPoints +
            "</td><td>" +
                              result[i].productType +
                              "</td></tr>";
        }
        output += "</tbody></table>";

        displayResources.html(output);
        $("table").addClass("table");
      }
    });
});
