<?php
    $request = json_decode(file_get_contents("php://input"));
    $num1 = $request->result->parameters->num1;
    $num2 = $request->result->parameters->num2;

    $speech = "Sum of " . $num1 . " and " . $num2 . " is " . ($num1+$num2);
    $response = array(
        "speech" => $speech,
        "displayText" => $speech,
    );

    header('Content-Type: application/json');
    echo json_encode($response);
?>