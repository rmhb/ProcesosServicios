    import json
    import sys
    pelicula = {"El Resplandor" :
        {
            "Director" : "Kubrick",
            "Year":1980,
            "Reparto": [
                {"Nombre" :"Jack Nicholson"},
                {"Nombre" : "sbelley Duvall"} ,
                {"Nombre" : "DannyLloyd"},
                {"Nombre" : "scatman Crothers" }
            ]
        }
    }
    print (json.dumps (pelicula ))
    sys.exit(0)