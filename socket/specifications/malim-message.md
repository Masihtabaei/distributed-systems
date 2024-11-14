# Malim Message Raw Text Sescription

## Malim Messgae Layout

Here you find a simple ASCII-description for a message defined by Malim-protocol.

| Index | Name | Length (in Bytes) | Data Type | Meaning
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | operationType | 4 | uint32_t | See Malim [operation types](#malim-operation-types)
| 1 | simulationTime | 4 | uint32_t | In Milliseconds
| 2 | Request/Response Parameters | X | - | See Malim [Request](#malim-request-parameters) and [Response](#malim-response-parameters) Layouts


## Malim Operation Types

Here you find current operations, their hexadecimal encoding, meaning and also some examples.

| Index | Name | Hexadecimal Encoding | Meaning | Example
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | ECHO | 0x0 | Returns the sent message | ECHO("Hello") = "Hello"
| 1 | SUM | 0x1 | Returns sum of the values given | SUM([1, 2, 3]) = 6
| 2 | COUNT | 0x2 | Returns number of occurences of positive integers in the given list | COUNT([-1, 1, 0, 2, -2, 3, 3]) = 4
| 3 | DISCONNECT | 0x3 | Disconnects the server | -
| 4 | QUIT | 0x4 | Quits the connection to the server | -

## Malim Request Parameters

Here you find layouts desired for requests for various operation types defined previously.

### Malim SUM and COUNT Request Parameters Layout

| Index | Name | Length (in Bytes) | Data Type | Meaning
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | numberOfParameters | 4 | uint32_t | Self-explaining
| 1 | parametersList | X | uint32_t | Self-explaining


### Malim ECHO, DISCONNECT and QUIT Request Parameters Layout

| Index | Name | Length (in Bytes) | Data Type | Meaning
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | messageLength | 4 | uint32_t | Number of characters in string
| 1 | message | X | string | UTF-8 (between 1 and 4 Bytes)

## Malim Response Parameters

Here you find layouts desired for responses of various operation types defined previously.

### Malim SUM and COUNT Response Parameters Layout

| Index | Name | Length (in Bytes) | Data Type | Meaning
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | result | 4 | uint32_t | Self-explaining


### Malim ECHO, DISCONNECT and QUIT Response Parameters Layout

| Index | Name | Length (in Bytes) | Data Type | Meaning
| ----------- | ----------- | ----------- | ----------- | -----------
| 0 | messageLength | 4 | uint32_t | Number of characters in the string
| 1 | message | X | string | UTF-8 (between 1 and 4 Bytes)
