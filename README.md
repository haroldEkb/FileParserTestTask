# FileParserTestTask

Test application that should show my basic skills as a Junior backend developer.

It is a REST API for parsing files into its component parts.
The file assumed to be a text file with possible line breaks in the CRLF, CR and / or LF style.
At the beginning of each line there can be a sign of the section or subsection beginning - the '#' symbol.
The number of '#' characters at the line beginning indicates the section nesting level.
The result is a parsed file in the form of a strings list and a structure of sections,
through which it will be possible to navigate through the sections on the client side.

At first I designed a class implementing tree-like structure
where each node contains section/subsection beginning line number,
its nesting level (depth) and list of its subsections. This variant is in the TreeStructure branch.
But eventually i decided that such structure is overcomplicated to work with at the client side.
I chose LinkedHashMap<Integer, Integer> as a structure representation
where key is section/subsection beginning line number and value is its nesting level (depth).
This variant is in the master branch.

---
**This api supports following methods:**
---
**Upload file**
----
  Returns json containing uploaded file id.

* **URL**

  /files

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
   None

* **Success Response:**

  * **Code:** 201 CREATED<br />
    **Content:** `{ "id" : 12 }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />

---
**Download file**
----
  Returns json containing uploaded file structure as key-value pair list of section beginning line number and depth pairs, and content as list of strings.

* **URL**

  /files/:id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** 
    `"structure": {
        "0": 1,
        "3": 2,
        "6": 1,
        "8": 1,
        "9": 2
    },
    "content": [
        "# First section",
        "Line",
        "Content",
        "## First subsection",
        "Some",
        "Text",
        "# Second section",
        "Words",
        "# Third section",
        "## Third subsection"
        "Line"
    ]`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
---
**Update file**
----
  Returns json containing uploaded file id.

* **URL**

  /files/:id

* **Method:**

  `PUT`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**

  If file already existed: <br />
  * **Code:** 200 OK<br />
  
  If file didn't exist: <br />
  * **Code:** 201 CREATED <br />
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
---
**Delete file**
----
  Deletes file on server

* **URL**

  /files/:id

* **Method:**

  `DELETE`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Success Response:**

  * **Code:** 200 <br />
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />

