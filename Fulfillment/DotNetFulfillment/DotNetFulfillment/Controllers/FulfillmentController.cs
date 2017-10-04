using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using DotNetFulfillment.Models;

namespace DotNetFulfillment.Controllers
{
    public class FulfillmentController : ApiController
    {
        public IHttpActionResult Post([FromBody] ApiAiRequest request)
        {
            int num1 = Convert.ToInt32(request.result.parameters["num1"]);
            int num2 = Convert.ToInt32(request.result.parameters["num2"]);
            int sum = num1 + num2;
            string speech = $"Sum of {num1} and {num2} is {sum}";
            ApiAiResponse response = new ApiAiResponse
            {
                speech = speech,
                displayText = speech
            };
            return Ok(response);
        }
    }
}
