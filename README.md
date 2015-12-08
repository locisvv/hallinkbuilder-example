# Simple hypermedia example with halbuilder

This example demonstrates how to use the halbuilder to build json in hal format.

For more information see the [HalBuilder User Guide](http://www.gotohal.net/halbuilder.html)

Some examples:
<http://localhost:8080/rest/albums/1> Accept:application/hal+json
```json
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/albums/rest/1"
        },
        "artist": {
            "href": "http://localhost:8080/artist/rest/1"
        }
    },
    "artistId": "1",
    "id": "1",
    "stockLevel": 2,
    "title": "Heritage"
}
```

<http://localhost:8080/rest/albums/1?embedded=true> Accept:application/hal+json

```json
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/albums/rest/1"
        },
        "artist": {
            "href": "http://localhost:8080/artist/rest/1"
        }
    },
    "artistId": "1",
    "id": "1",
    "stockLevel": 2,
    "title": "Heritage",
    "_embedded": {
        "artist": {
            "_links": {
                "self": {
                    "href": "http://localhost:8080/artist/rest/1"
                }
            },
            "id": "1",
            "name": "Opeth"
        }
    }
}
```
# Running the Example

1. Open this project in Intellij.
2. Create new local tomcat Run/Debug confihuration.
3. Add new Build Artifacts in the "Before launch: Make, Build Artifacts"  and select "jersey-declarative-linking:war exploded".
5. Select available tomcat version.
6. Open deployment tab and also add "jersey-declarative-linking:war exploded".
7. Save, close and now you can run this project.
