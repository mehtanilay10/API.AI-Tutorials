import json
import os

from flask import Flask
from flask import request
from flask import make_response

# Flask app should start in global layout
app = Flask(__name__)


@app.route('/calc', methods=['POST'])
def calc():
    req = request.get_json(silent=True, force=True)
    result = req.get("result")

    num1 = int(result.get("parameters").get('num1'))
    num2 = int(result.get("parameters").get('num2'))
    speech = ("Sum of {0} and {1} is {2}".format(num1, num2, num1 + num2))
    res = {
        "speech": speech,
        "displayText": speech,
    }
    res = json.dumps(res, indent=4)
    r = make_response(res)
    r.headers['Content-Type'] = 'application/json'
    return r


if __name__ == '__main__':
    port = int(os.getenv('PORT', 5000))
    print("Starting app on port %d" % port)
    app.run()



# Create simple API using Python
# 1] Create app.py, create app using Flask
# 2] Create a route, retrieve parametes & perform operation, return response
# 3] run app


# Publish to GCloud
# 1] Create app.yaml
# 2] create requirement.txt
# 3] gcloud init
# 4] login
# 5] select project
# 6] gcloud app deploy
# 7] gcloud app browse