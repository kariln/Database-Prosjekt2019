# Database-Prosjekt2019


## Relasjonsdatabasemodell (tabellform)


Bruker

| <u>ID</u> | Navn |
|---|---|

Bruker-økt

| <u> Bruker-ID </u> | <u>Økt-ID</u> |
|---|---|


Treningsøkt

| <u>Økt-ID</u> | Dato-tidspunkt | Varighet |
|---|---|

Notat

| <u>Økt-ID</u> | Formål | Opplevelse | Diverse |
| --- | --- | ---- |

Resultat

| <u>Økt-ID</u> | Form | Prestasjon |
|---|---|

Økt-øvelse

| <u>Øvelse-ID</u> | <u>Økt-ID</u> |
|---|

Logg

| <u>Økt-ID</u> | <u> Øvelse-ID </u> | Sett | Repetisjoner | Kg |
|--|

Øvelse

| <u>Øvelse-ID</u> | Navn |
|---|

Øvelse-gruppe

| <u>  Øvelse-ID </u> | <u> Øvelsesgruppe-ID </u>
| --- |

Øvelsesgruppe

| <u> Øvelsesgruppe-ID </u> | Navn | Beskrivelse |
| --- |

Ikke_fastmontert_øvelse

|  |  |
|---|

Fastmontert-øvelse

| |   |
|---|

Apparat

|<u>Apparat-ID</u>| Navn | Brukerinstruks |
|--|

Øvelse_apparat

|<u>apparat_id</u> | <u>øvelse_id</u> |
|---|
