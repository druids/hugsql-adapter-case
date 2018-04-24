HugSQL Adapter Case
===================

This is a [HugSQL](https://www.hugsql.org) adapter that converts SQL columns into required case (e.g. snake-case).

[![CircleCI](https://circleci.com/gh/druids/hugsql-adapter-case.svg?style=svg)](https://circleci.com/gh/druids/hugsql-adapter-case)
[![Dependencies Status](https://jarkeeper.com/druids/hugsql-adapter-case/status.png)](https://jarkeeper.com/druids/hugsql-adapter-case)
[![License](https://img.shields.io/badge/MIT-Clause-blue.svg)](https://opensource.org/licenses/MIT)


Leiningen/Boot
--------------

```clojure
[hugsql-adapter-case "0.1.0"]
```


Documentation
-------------

This plugin **DOES NOT** contain HugSQL itself (please require it in you `project.clj`).

Simple usage:

```clojure
(require '[hugsql-adapter-case.adapters :refer [kebab-adapter]])
(require '[hugsql.core :as hugsql])

(hugsql/def-db-fns "myproject/db.sql" {:adapter (kebab-adapter)})
```

Now all you HugSQL queries should return snake-cased column names.

Case adapters:

  * kebab-adapter - converts to `kebab-case`
  * snake-adapter - converts to `snake_case`
  * camel-adapter - converts to `camelCase`


Contribution
------------

### Conventions

* Please follow coding style defined by [`.editorconfig`](http://editorconfig.org)
 and [The Clojure Style Guide](https://github.com/bbatsov/clojure-style-guide)
* Write [good commit messages](https://chris.beams.io/posts/git-commit/)
 and provide an issue ID in a commit message prefixed by `#`
